package com.easyen.easyenglish.serviceimpl;

import com.easyen.easyenglish.entity.correctionFeedback;
import com.easyen.easyenglish.entity.essay;
import com.easyen.easyenglish.mapper.correctionFeedbackMapper;
import com.easyen.easyenglish.service.correctionFeedbackService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class correctionFeedbackServiceImpl implements correctionFeedbackService {
    @Autowired
    correctionFeedbackMapper correctionFeedbackMapper;

    @Override
    public void addFeedback(correctionFeedback correctionFeedback){
        try {
            correctionFeedbackMapper.addFeedback(correctionFeedback);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("添加Feedback失败：" + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteFeedback(Integer feedbackId) {
        try {
            correctionFeedbackMapper.deleteFeedback(feedbackId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("删除Feedback失败：" + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void updateFeedback(correctionFeedback correctionFeedback) {
        correctionFeedbackMapper.updateFeedback(correctionFeedback);
    }

    @Override
    public correctionFeedback findByID(Integer feedbackId){return correctionFeedbackMapper.findByID(feedbackId);}

    @Override
    public List<correctionFeedback> findByEssay(Integer essayID){
        return correctionFeedbackMapper.findByEssay(essayID);
    }

    @Override
    public String generateSuggestion(String requirements, String originEssay) {
        // 这里可以和Score复用但是好麻烦我copy了，可以用if优化一下
        // 下方输入api key
        String token = "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";
        OpenAiService service = new OpenAiService(token);

        // 构建ChatGPT请求
        CompletionRequest completionRequest = CompletionRequest.builder()
                .model("text-davinci-003")
                .prompt("请你返回批改意见。我的批改点是："+requirements+"。我的作文是："+originEssay)
                .temperature(0.5)
                .maxTokens(2048)
                .topP(1D)
                .frequencyPenalty(0D)
                .presencePenalty(0D)
                .build();
        CompletionChoice choice = service.createCompletion(completionRequest).getChoices().get(0);
        String generatedText = choice.getText();
        return generatedText;
    }

    @Override
    public String generateScore(String requirements, String originEssay) {
        // 下方输入api key
        String token = "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";
        OpenAiService service = new OpenAiService(token);

        // 构建ChatGPT请求
        CompletionRequest completionRequest = CompletionRequest.builder()
                .model("text-davinci-003")
                .prompt("请注意！满分是一百分，你只能回答一个数字。我的批改点是："+requirements+"。我的作文是："+originEssay)
                .temperature(0.5)
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