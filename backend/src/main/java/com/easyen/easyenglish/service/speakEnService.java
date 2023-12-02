package com.easyen.easyenglish.service;
import java.util.List;

import com.easyen.easyenglish.entity.speakPractice;

public interface speakEnService {
    public List<String> getAllPracticeQuestions();
    public List<String> getPracticeQuestionsByDate(String date);
    public int update(speakPractice SpeakPractice);
    public int add(speakPractice speakP);
}