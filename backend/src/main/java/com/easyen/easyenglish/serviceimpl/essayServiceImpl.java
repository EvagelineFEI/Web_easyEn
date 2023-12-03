package com.easyen.easyenglish.serviceimpl;
import com.alibaba.fastjson2.JSONObject;
import com.easyen.easyenglish.mapper.essayMapper;
import com.easyen.easyenglish.entity.essay;
import com.easyen.easyenglish.service.essayService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class essayServiceImpl implements essayService {
    @Autowired
    essayMapper essayMapper;

    @Override
    public List<essay> getAllEssays() {
        return essayMapper.getAllEssays();
    }

    @Override
    public String generateEssay(String requirements, String originEssay) {
        // 下方输入api key
        String token = "";
        OpenAiService service = new OpenAiService(token);

        // 构建ChatGPT请求
        CompletionRequest completionRequest = CompletionRequest.builder()
                .model("text-davinci-003")
                .prompt("我的要求是："+requirements+"。我的作文是："+originEssay)
                .temperature(0.7)
                .maxTokens(2048)
                .topP(1D)
                .frequencyPenalty(0D)
                .presencePenalty(0D)
                .build();
        CompletionChoice choice = service.createCompletion(completionRequest).getChoices().get(0);
        String generatedText = choice.getText();
        return generatedText;
    }
}
