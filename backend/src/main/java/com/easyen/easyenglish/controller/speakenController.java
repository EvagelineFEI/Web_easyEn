package com.easyen.easyenglish.controller;
import com.easyen.easyenglish.dto.topicReq;
import com.easyen.easyenglish.entity.essay;
import com.easyen.easyenglish.entity.speakEnPracticeRecord;
import com.easyen.easyenglish.service.speakEnRecordService;
import com.easyen.easyenglish.dto.Result;
import com.easyen.easyenglish.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/speaken")
public class speakenController {
    @Autowired
    speakEnRecordService speakEnService;
    @PostMapping("/speakFeedback")
    public Result getChat(@RequestHeader("Authorization") String userJWT,@RequestBody topicReq topicReq){
        try {
            System.out.println("request body: " + topicReq);
            String ans = speakEnService.getSpeakResponce(topicReq.getRequirements(), topicReq.getTopic());
            return Result.success(ans);
        } catch (Exception e) {
            return Result.failure("发生未知错误：" + e.getMessage());
        }
    }
    @PostMapping("/speak-upload")
    public Result uploadChat(@RequestHeader("Authorization") String userJWT,@RequestBody speakEnPracticeRecord record) {
        try {
            Integer user_id = JwtUtil.getUserIdByJWT(userJWT);
            record.setUser_id(user_id);
            speakEnService.addRecord(record);
            return Result.success(record);
        } catch (Exception e) {
            return Result.failure("发生未知错误：" + e.getMessage());
        }
    }
    // 查询数据库所有练习记录
    @GetMapping("/findAll")
    public Result getAllRecord() {
        try {
            List<speakEnPracticeRecord> records = speakEnService.getAllrecord();
            return Result.success(records);
        } catch (Exception e) {
            return Result.failure("发生未知错误：" + e.getMessage());
        }
    }
    // 按照话题查询数据库练习记录
    @GetMapping("/findAll/{topic}")
    public Result getByTopic(@PathVariable String topic){
        try {
            List<speakEnPracticeRecord> record = speakEnService.findByTopic(topic);
            return Result.success(record);
        } catch (Exception e) {
            return Result.failure("发生未知错误：" + e.getMessage());
        }
    }
}
