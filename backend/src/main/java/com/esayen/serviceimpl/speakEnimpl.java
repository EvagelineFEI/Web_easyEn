package com.easyen.serviceimpl;

import com.easyen.entity.speakPractice;
import com.easyen.mapper.speakPracticemapper;
import com.easyen.service.speakEnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class serviceimpl implements speakEnService{
    @Autowired  //实现依赖注入
    private speakPracticemapper spmapper;

    public List<String> getAllPracticeQuestions(){
        return spmapper.getAllPracticeQuestions();
    }
    public List<String> getPracticeQuestionsByDate(string date){
        return spmapper.getPracticeQuestionsByDate(date);
    }
    public int update(SpeakPractice speakPractice){
        return spmapper.update(speakPractice);
    }
    public int add(speakPractice speakP){
        return spmapper.add(speakP);
    }
}