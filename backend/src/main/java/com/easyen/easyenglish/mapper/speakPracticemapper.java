// package com.easyen.easyenglish.mapper;

// import java.util.List;

// import org.apache.ibatis.annotations.*;

// import com.easyen.easyenglish.entity.speakPractice;

// @Mapper
// public interface speakPracticemapper {
//         @Select("select practiced_topic_list,topic_answer_list from speakEnPracticeRecord")
//         public List<String> getAllPracticeQuestions();

//         @Select("select practiced_topic_list,topic_answer_list from speakEnPracticeRecord where date = #{date}")
//         public List<String> getPracticeQuestionsByDate(String date);

//         @Update("UPDATE speakEnPracticeRecord SET date = #{practiceDate}, " +
//                         "practiced_topic_list = #{oralQuestions}, topic_answer_list = #{aiAnswers} " +
//                         "WHERE id = #{recordId}")
//         public int update(speakPractice SpeakPractice);

//         @Options(useGeneratedKeys = true, keyProperty = "id")
//         @Insert("insert into speakEnPracticeRecord(id,user_id,date,practiced_topic_list,topic_answer_list) " +
//                         "values(#{recordId},#{user_id},#{date},#{oralQuestions},#{aiAnswers})")
//         public int add(speakPractice speakP);
// }