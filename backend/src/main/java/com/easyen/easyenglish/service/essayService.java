package com.easyen.easyenglish.service;

import com.easyen.easyenglish.entity.essay;

import java.util.List;

public interface essayService {
    List<essay> getAllEssays();
    essay findByID(Integer essayId);
    List<essay> findByUser(Integer userID);
    List<essay> findEssaysByTitle(essay essay);
    void addEssay(essay essay);
    void deleteEssay(Integer essayId);
    void updateEssay(essay essay);
}
