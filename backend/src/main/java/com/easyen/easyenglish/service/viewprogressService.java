package com.easyen.easyenglish.service;

import com.easyen.easyenglish.entity.essay;
import com.easyen.easyenglish.entity.speakEnPracticeRecord;

import java.util.List;

public interface viewprogressService {
    float getCert4P();
    float getCert6P();
    Integer getWordsnum(Integer user_id);
    Integer getSpeaknum(Integer user_id);
    Integer getEssaynum(Integer user_id);
    List<speakEnPracticeRecord> getSpeakP(Integer user_id);
    List<essay> getEssayP(Integer user_id);

}
