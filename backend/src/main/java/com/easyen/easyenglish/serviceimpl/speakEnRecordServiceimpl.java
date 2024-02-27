package com.easyen.easyenglish.serviceimpl;

import com.easyen.easyenglish.entity.speakEnPracticeRecord;
import com.easyen.easyenglish.mapper.speakEnRecordMapper;
import com.easyen.easyenglish.service.speakEnRecordService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.OpenAiApi;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Value;
import retrofit2.Retrofit;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.time.Duration;
import java.util.List;

import static com.theokanning.openai.service.OpenAiService.*;

@Service
public class speakEnRecordServiceimpl implements speakEnRecordService {
    @Autowired
    speakEnRecordMapper speakEnRecordMapper;
    @Override
    @Transactional
    public void addRecord(speakEnPracticeRecord record) {
        try {
            speakEnRecordMapper.addRecord(record);
        } catch (Exception e) {
            // 在实际应用中，你可能会记录日志或者执行其他适当的异常处理操作
            e.printStackTrace();
            throw new RuntimeException("添加口语记录失败：" + e.getMessage());
        }
    }

    @Override
    public void deleteRecord(Integer record) {
        try {
            speakEnRecordMapper.deleteRecord(record);
        } catch (Exception e) {
            // 在实际应用中，你可能会记录日志或者执行其他适当的异常处理操作
            e.printStackTrace();
            throw new RuntimeException("更新口语记录失败：" + e.getMessage());
        }
    }

    @Override
    public void updateRecord(speakEnPracticeRecord record) {
        try {
            speakEnRecordMapper.updateRecord(record);
        } catch (Exception e) {
            // 在实际应用中，你可能会记录日志或者执行其他适当的异常处理操作
            e.printStackTrace();
            throw new RuntimeException("更新口语记录失败：" + e.getMessage());
        }
    }

    @Override
    public List<speakEnPracticeRecord> getAllrecord() {
        return speakEnRecordMapper.getAllRecord();
    }

    @Override
    public List<speakEnPracticeRecord> findByTopic(String topic) {
        return speakEnRecordMapper.findByTopic(topic);
    }
    @Value("${gpt.api-key}")
    String token;
    @Value("${gpt.model}")
    String model;
    @Value("${gpt.temperature}")
    Double t;
    @Value("${gpt.maxTokens}")
    Integer maxt;
    @Value("${gpt.timeout}")
    Duration timeout;
    @Value("${proxy.host}")
    String host;
    @Value("${proxy.port}")
    Integer port;

    @Override
    public String getSpeakResponce(String requirements,String question) {
        // Configure the proxy
            ObjectMapper mapper = defaultObjectMapper();
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port));
            OkHttpClient client = defaultClient(token, timeout)
                    .newBuilder()
                    .proxy(proxy)
                    .build();
            Retrofit retrofit = defaultRetrofit(client, mapper);
            OpenAiApi api = retrofit.create(OpenAiApi.class);
            OpenAiService service = new OpenAiService(api);
            CompletionRequest completionRequest = CompletionRequest.builder()
                    .model(model)
                    .prompt("我现在在进行"+requirements+"口语话题的练习。我练习的话题或问题是："+question+"。请依据"+requirements+"评分标准，给出一个可能的得到高分的回答")
                    .temperature(t)
                    .maxTokens(maxt)
                    .topP(1D)
                    .frequencyPenalty(0D)
                    .presencePenalty(0D)
                    .build();
            System.out.println("构建完成，正在请求"+completionRequest);
            CompletionChoice choice = service.createCompletion(completionRequest).getChoices().get(0);
            String generatedText = choice.getText();
            return generatedText;
    }


}
