package com.easyen.easyenglish.serviceimpl;

import com.easyen.easyenglish.entity.essay;
import com.easyen.easyenglish.entity.speakEnPracticeRecord;
import com.easyen.easyenglish.mapper.viewprogressMapper;
import com.easyen.easyenglish.service.viewprogressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class viewprogressServiceImpl implements viewprogressService {
    @Autowired
    viewprogressMapper viewMapper;
    @Override
    public float getCert4P() {
        return viewMapper.getCert4P();
    }
    @Override
    public float getCert6P() {
        return viewMapper.getCert6P();
    }

    @Override
    public Integer getWordsnum(Integer user_id) {
        return viewMapper.getWordsnum(user_id);
    }

    @Override
    public Integer getSpeaknum(Integer user_id) {
        return viewMapper.getSpeaknum(user_id);
    }

    @Override
    public Integer getEssaynum(Integer user_id) {
        return viewMapper.getEssaynum(user_id);
    }
    @Override
    public List<speakEnPracticeRecord> getSpeakP(Integer user_id) {
        return viewMapper.getSpeakP(user_id);
    }
    @Override
    public List<essay> getEssayP(Integer user_id) {
        return viewMapper.getEssayP(user_id);
    }
}
