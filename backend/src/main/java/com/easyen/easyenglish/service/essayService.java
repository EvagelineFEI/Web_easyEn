package com.easyen.easyenglish.service;

import com.easyen.easyenglish.entity.essay;

import java.util.List;

public interface essayService {
    List<essay> getAllEssays();
    String generateEssay(String requirements, String originEssay);
}
