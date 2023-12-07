package com.easyen.easyenglish.serviceimpl;

import com.easyen.easyenglish.entity.speakEnPracticeRecord;
import com.easyen.easyenglish.mapper.speakEnRecordMapper;
import com.easyen.easyenglish.service.speakEnRecordService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Value;
import java.util.List;

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
    public speakEnPracticeRecord findByTopic(String topic) {
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
    @Override
    public String getSpeakResponce(String question) {
        OpenAiService service = new OpenAiService(token);
        // 构建ChatGPT请求
        CompletionRequest completionRequest = CompletionRequest.builder()
                .model(model)
                .prompt("我现在在进行雅思口语话题的练习。我练习的话题或问题是："+question+"。请依据雅思评分标准，给出一个可能的得到高分的回答")
                .temperature(t)
                .maxTokens(maxt)
                .topP(1D)
                .frequencyPenalty(0D)
                .presencePenalty(0D)
                .build();
        CompletionChoice choice = service.createCompletion(completionRequest).getChoices().get(0);
        String generatedText = choice.getText();
        return generatedText;
    }


}
