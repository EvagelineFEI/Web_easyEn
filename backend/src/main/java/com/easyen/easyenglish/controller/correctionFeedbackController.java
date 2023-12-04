package com.easyen.easyenglish.controller;

import com.easyen.easyenglish.dto.essayGenerate;
import com.easyen.easyenglish.entity.correctionFeedback;
import com.easyen.easyenglish.entity.essay;
import com.easyen.easyenglish.service.correctionFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suggestion")
public class correctionFeedbackController {
    @Autowired
    correctionFeedbackService correctionFeedbackService;

    // 增加批改记录，成功返回200
    @PostMapping("/upload")
    public int addFeedback(@RequestBody correctionFeedback correctionFeedback) {
        correctionFeedbackService.addFeedback(correctionFeedback);
        return 200;
    }

    // 删除批改记录，成功返回200
    @DeleteMapping("/delete/{feedbackId}")
    public int deleteFeedback(@PathVariable Integer feedbackId) {
        correctionFeedbackService.deleteFeedback(feedbackId);
        return 200;
    }

    // 更新批改记录，成功返回200
    @PutMapping("/update")
    public int updateFeedback(@RequestBody correctionFeedback correctionFeedback) {
        correctionFeedbackService.updateFeedback(correctionFeedback);
        return 200;
    }

    // 根据批改记录号查询批改记录
    @GetMapping("/findById/{feedbackId}")
    public correctionFeedback findByID(@PathVariable Integer feedbackId) {
        return correctionFeedbackService.findByID(feedbackId);
    }

    // 根据文章号查询批改记录
    @GetMapping("/findByEssay/{essayId}")
    public List<correctionFeedback> findByEssay(@PathVariable Integer essayId) {
        return correctionFeedbackService.findByEssay(essayId);
    }

    // 传入批改要点requirement（String）、批改作文originalEssay（String），返回批改建议（String）
    @PostMapping("/Suggestion")
    public String getChatResponse(@RequestBody essayGenerate essayGenerate){
        return correctionFeedbackService.generateSuggestion(essayGenerate.getRequirements(),essayGenerate.getOriginalEssay());
    }

    // 传入批改要点requirement（String）、批改作文originalEssay（String），返回批改分数（String）
    @PostMapping("/essayScore")
    public String getChatScore(@RequestBody essayGenerate essayGenerate){
        return correctionFeedbackService.generateScore(essayGenerate.getRequirements(),essayGenerate.getOriginalEssay());
    }
}
