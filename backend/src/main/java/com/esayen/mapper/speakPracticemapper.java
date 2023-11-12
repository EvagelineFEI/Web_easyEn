package com.easyen.mapper
import org.apache.ibatis.annotations.*;
import java.util.List;

import com.exam.entity.speakPractice;
@Mapper
public interface speakPracticeMapper{
    @Select("select practiced_topic_list,topic_answer_list from speakEnPracticeRecord")
    public List<String> getAllPracticeQuestions();
    @Select("select practiced_topic_list,topic_answer_list from speakEnPracticeRecord where date = #{date}")
    public List<String> getPracticeQuestionsByDate(String date);

    @Update("UPDATE speakEnPracticeRecord SET date = #{practiceDate}, " +
            "practiced_topic_list = #{oralQuestions}, topic_answer_list = #{aiAnswers} " +
            "WHERE id = #{recordId}")
    public int update(SpeakPractice speakPractice);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into speakEnPracticeRecord(id,user_id,date,practiced_topic_list,topic_answer_list) " +
            "values(#{recordId},#{user_id},#{date},#{oralQuestions},#{aiAnswers})")
    public int add(speakPractice speakP);
}