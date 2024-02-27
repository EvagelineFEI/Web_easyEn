package com.easyen.easyenglish.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.easyen.easyenglish.entity.speakEnPracticeRecord;
import com.easyen.easyenglish.entity.essay;

import java.util.List;

@Mapper
public interface viewprogressMapper {
    float getCert4P();
    float getCert6P();
    Integer getWordsnum(Integer user_id);
    Integer getSpeaknum(Integer user_id);
    Integer getEssaynum(Integer user_id);
    List<speakEnPracticeRecord> getSpeakP(Integer user_id);
    List<essay> getEssayP(Integer user_id);
}
