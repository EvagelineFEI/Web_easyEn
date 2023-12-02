// package com.easyen.easyenglish.serviceimpl;


// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.easyen.easyenglish.entity.speakPractice;
// import com.easyen.easyenglish.mapper.speakPracticemapper;
// import com.easyen.easyenglish.service.speakEnService;

// @Service
// public class speakEnimpl implements speakEnService{
//     @Autowired  //实现依赖注入
//     private speakPracticemapper spmapper;

//     public List<String> getAllPracticeQuestions(){
//         return spmapper.getAllPracticeQuestions();
//     }
//     public List<String> getPracticeQuestionsByDate(String date){
//         return spmapper.getPracticeQuestionsByDate(date);
//     }
//     public int update(speakPractice SpeakPractice){
//         return spmapper.update(SpeakPractice);
//     }
//     public int add(speakPractice speakP){
//         return spmapper.add(speakP);
//     }
// }