package com.easyen.easyenglish.serviceimpl;
import com.easyen.easyenglish.mapper.essayMapper;
import com.easyen.easyenglish.entity.essay;
import com.easyen.easyenglish.service.essayService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class essayServiceImpl implements essayService {
    @Autowired
    essayMapper essayMapper;

    @Override
    public List<essay> getAllEssays() {
        return essayMapper.getAllEssays();
    }

    @Override
    public essay findByID(Integer essayId){return essayMapper.findByID(essayId);}

    @Override
    public List<essay> findByUser(Integer userID){
        return essayMapper.findByUser(userID);
    }

    @Override
    public List<essay> findEssaysByTitle(String essayTitle) {
        return essayMapper.findEssaysByTitle(essayTitle);
    }

    @Override
    @Transactional
    public void addEssay(essay essay) {
        try {
            essayMapper.addEssay(essay);
        } catch (Exception e) {
            // 在实际应用中，你可能会记录日志或者执行其他适当的异常处理操作
            e.printStackTrace();
            throw new RuntimeException("添加Essay失败：" + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteEssay(Integer essayId) {
        try {
            essayMapper.deleteEssay(essayId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("删除Essay失败：" + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void updateEssay(essay essay) {
        essayMapper.updateEssay(essay);
    }
}
