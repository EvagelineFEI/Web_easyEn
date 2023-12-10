package com.easyen.easyenglish.controller;

import com.easyen.easyenglish.dto.Result;
import com.easyen.easyenglish.dto.essayGenerate;
import com.easyen.easyenglish.entity.correctionFeedback;
import com.easyen.easyenglish.entity.essay;
import com.easyen.easyenglish.service.correctionFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/suggestion")
public class correctionFeedbackController {
    @Autowired
    correctionFeedbackService correctionFeedbackService;

    // 增加批改记录，成功返回200
    @PostMapping("/upload")
    public Result addFeedback(@RequestBody correctionFeedback correctionFeedback) {
        try {
            correctionFeedbackService.addFeedback(correctionFeedback);
            return new Result(correctionFeedback, 200);
        } catch (Exception e) {
            return new Result("发生未知错误：" + e.getMessage(), 500);
        }
    }

    // 删除批改记录，成功返回200
    @DeleteMapping("/delete/{feedbackId}")
    public Result deleteFeedback(@PathVariable Integer feedbackId) {
        try {
            correctionFeedbackService.deleteFeedback(feedbackId);
            return new Result(feedbackId, 200);
        } catch (Exception e) {
            return new Result("发生未知错误：" + e.getMessage(), 500);
        }
    }

    // 更新批改记录，成功返回200
    @PutMapping("/update")
    public Result updateFeedback(@RequestBody correctionFeedback correctionFeedback) {
        try {
            correctionFeedbackService.updateFeedback(correctionFeedback);
            return new Result(correctionFeedback, 200);
        } catch (Exception e) {
            return new Result("发生未知错误：" + e.getMessage(), 500);
        }
    }

    // 根据批改记录号查询批改记录
    @GetMapping("/findById/{feedbackId}")
    public Result findByID(@PathVariable Integer feedbackId) {
        try {
            correctionFeedback feedback = correctionFeedbackService.findByID(feedbackId);
            return new Result(feedback, 200);
        } catch (Exception e) {
            return new Result("发生未知错误：" + e.getMessage(), 500);
        }
    }

    // 根据文章号查询批改记录
    @GetMapping("/findByEssay/{essayId}")
    public Result findByEssay(@PathVariable Integer essayId) {
        try {
            List<correctionFeedback> feedbackList = correctionFeedbackService.findByEssay(essayId);
            return new Result(feedbackList, 200);
        } catch (Exception e) {
            return new Result("发生未知错误：" + e.getMessage(), 500);
        }
    }
    // 传入批改要点requirement（String）、批改作文originalEssay（String），返回批改建议（String）
    @PostMapping("/Suggestion")
    public Result getChatResponse(@RequestBody essayGenerate essayGenerate){
        try {
            String suggestion = correctionFeedbackService.generateSuggestion(essayGenerate.getRequirements(), essayGenerate.getOriginalEssay());
            return new Result(suggestion, 200);
        } catch (Exception e) {
            return new Result("发生未知错误：" + e.getMessage(), 500);
        }
    }

    // 传入批改要点requirement（String）、批改作文originalEssay（String），返回批改分数（String）
    @PostMapping("/essayScore")
    public Result getChatScore(@RequestBody essayGenerate essayGenerate){
        try {
            String score = correctionFeedbackService.generateScore(essayGenerate.getRequirements(), essayGenerate.getOriginalEssay());
            return new Result(score, 200);
        } catch (Exception e) {
            return new Result("发生未知错误：" + e.getMessage(), 500);
        }
    }
}
