package com.easyen.easyenglish.service;

import com.easyen.easyenglish.entity.correctionFeedback;
import com.easyen.easyenglish.entity.essay;

import java.util.List;

public interface correctionFeedbackService {
    void addFeedback(correctionFeedback correctionFeedback);
    void deleteFeedback(Integer feedbackId);
    void updateFeedback(correctionFeedback correctionFeedback);
    correctionFeedback findByID(Integer feedbackId);
    List<correctionFeedback> findByEssay(Integer essayID);
    String generateSuggestion(String requirements, String originEssay);
    String generateScore(String requirements, String originEssay);
}
