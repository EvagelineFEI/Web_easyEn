package com.easyen.easyenglish.controller;

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
    public ResponseEntity<String> addFeedback(@RequestBody correctionFeedback correctionFeedback) {
        correctionFeedbackService.addFeedback(correctionFeedback);
        return ResponseEntity.ok("{\"code\": 200}");
    }

    // 删除批改记录，成功返回200
    @DeleteMapping("/delete/{feedbackId}")
    public ResponseEntity<String> deleteFeedback(@PathVariable Integer feedbackId) {
        correctionFeedbackService.deleteFeedback(feedbackId);
        return ResponseEntity.ok("{\"code\": 200}");
    }

    // 更新批改记录，成功返回200
    @PutMapping("/update")
    public ResponseEntity<String> updateFeedback(@RequestBody correctionFeedback correctionFeedback) {
        correctionFeedbackService.updateFeedback(correctionFeedback);
        return ResponseEntity.ok("{\"code\": 200}");
    }

    // 根据批改记录号查询批改记录
    @GetMapping("/findById/{feedbackId}")
    public ResponseEntity<Object> findByID(@PathVariable Integer feedbackId) {
        correctionFeedback feedback = correctionFeedbackService.findByID(feedbackId);
        if (feedback != null) {
            return ResponseEntity.ok(Map.of("code", 200, "data", feedback));
        } else {
            return ResponseEntity.ok(Map.of("code", 500, "data", feedback));
        }
    }

    // 根据文章号查询批改记录
    @GetMapping("/findByEssay/{essayId}")
    public ResponseEntity<Object> findByEssay(@PathVariable Integer essayId) {
        List<correctionFeedback> feedbackList = correctionFeedbackService.findByEssay(essayId);
        if (!feedbackList.isEmpty()) {
            return ResponseEntity.ok(Map.of("code", 200, "data", feedbackList));
        } else {
            return ResponseEntity.ok(Map.of("code", 500, "data", feedbackList));
        }
    }

    // 传入批改要点requirement（String）、批改作文originalEssay（String），返回批改建议（String）
    @PostMapping("/Suggestion")
    public ResponseEntity<Object> getChatResponse(@RequestBody essayGenerate essayGenerate){
        String suggestion = correctionFeedbackService.generateSuggestion(essayGenerate.getRequirements(), essayGenerate.getOriginalEssay());
        if (suggestion != null) {
            return ResponseEntity.ok(Map.of("code", 200, "suggestion", suggestion));
        } else {
            return ResponseEntity.status(500).body("Failed to generate suggestion.");
        }
    }

    // 传入批改要点requirement（String）、批改作文originalEssay（String），返回批改分数（String）
    @PostMapping("/essayScore")
    public ResponseEntity<Object> getChatScore(@RequestBody essayGenerate essayGenerate){
        String score = correctionFeedbackService.generateScore(essayGenerate.getRequirements(), essayGenerate.getOriginalEssay());
        if (score != null) {
            return ResponseEntity.ok(Map.of("code", 200, "score", score));
        } else {
            return ResponseEntity.status(500).body("Failed to generate score.");
        }
    }
}
