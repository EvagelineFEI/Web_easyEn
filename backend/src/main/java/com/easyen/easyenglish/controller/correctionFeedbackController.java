package com.easyen.easyenglish.controller;

import com.easyen.easyenglish.dto.Result;
import com.easyen.easyenglish.dto.essayGenerate;
import com.easyen.easyenglish.entity.correctionFeedback;
import com.easyen.easyenglish.entity.essay;
import com.easyen.easyenglish.service.correctionFeedbackService;
import com.easyen.easyenglish.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suggestion")
public class correctionFeedbackController {
    @Autowired
    correctionFeedbackService correctionFeedbackService;

    // 增加批改记录，成功返回200
    // 把这个接口和获取批改建议整合起来了
    @PostMapping("/upload")
    public Result addFeedback(@RequestBody correctionFeedback correctionFeedback, @RequestHeader("Authorization") String userJWT) {
        try {
            Integer user_id = JwtUtil.getUserIdByJWT(userJWT);
            correctionFeedbackService.addFeedback(correctionFeedback);
            return Result.successCode();
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }

    // 删除批改记录，成功返回200
    @DeleteMapping("/delete/{feedbackId}")
    public Result deleteFeedback(@PathVariable Integer feedbackId, @RequestHeader("Authorization") String userJWT) {
        try {
            Integer user_id = JwtUtil.getUserIdByJWT(userJWT);
            correctionFeedbackService.deleteFeedback(feedbackId);
            return Result.successCode();
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }

    // 更新批改记录，成功返回200
    // 由于批改记录是提交作文后才有的，作文提交做了鉴权，所以这里不做鉴权
    @PutMapping("/update")
    public Result updateFeedback(@RequestBody correctionFeedback correctionFeedback) {
        try {
            correctionFeedbackService.updateFeedback(correctionFeedback);
            return Result.successCode();
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }

//    // 根据批改记录号查询批改记录
//    @GetMapping("/findById/{feedbackId}")
//    public Result findByID(@PathVariable Integer feedbackId, @RequestHeader("Authorization") String userJWT) {
//        try {
//            Integer user_id = JwtUtil.getUserIdByJWT(userJWT);
//            correctionFeedback feedback = correctionFeedbackService.findByID(feedbackId);
//            return Result.success(feedback);
//        } catch (Exception e) {
//            return Result.failure(e.getMessage());
//        }
//    }

    // 根据文章号查询批改记录
    // 显示作文对应的批改记录
    @GetMapping("/findByEssay/{essayId}")
    public Result findByEssay(@PathVariable Integer essayId) {
        try {
//            Integer user_id = JwtUtil.getUserIdByJWT(userJWT);
            List<correctionFeedback> feedbackList = correctionFeedbackService.findByEssay(essayId);
            return Result.success(feedbackList);
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }

    // 传入批改要点requirement（String）、批改作文originalEssay（String），返回批改建议（String）
    @PostMapping("/Suggestion")
    public Result getChatResponse(@RequestHeader("Authorization") String userJWT,@RequestBody essay essay) {
        try {
            Integer user_id = JwtUtil.getUserIdByJWT(userJWT);
            String suggestion = correctionFeedbackService.generateSuggestion(essay.getEssay_requirements(),essay.getEssay_title(),essay.getEssay_content());
            correctionFeedback correctionFeedback = new correctionFeedback();
            correctionFeedback.setEssay_id(essay.getEssay_id());
            correctionFeedback.setCorrection_suggestions(suggestion);
            return Result.success(correctionFeedback);
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }

    // 传入批改要点requirement（String）、批改作文originalEssay（String），返回批改分数（String）
    @PostMapping("/essayScore")
    public Result getChatScore(@RequestBody essayGenerate essayGenerate, @RequestHeader("Authorization") String userJWT) {
        try {
            Integer user_id = JwtUtil.getUserIdByJWT(userJWT);
            String score = correctionFeedbackService.generateScore(essayGenerate.getRequirements(), essayGenerate.getEssay_content());
            return Result.success(score);
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }
}