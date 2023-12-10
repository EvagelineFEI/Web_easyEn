package com.easyen.easyenglish.controller;
import com.easyen.easyenglish.dto.topicReq;
import com.easyen.easyenglish.entity.essay;
import com.easyen.easyenglish.entity.speakEnPracticeRecord;
import com.easyen.easyenglish.service.speakEnRecordService;
import com.easyen.easyenglish.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/speaken")
public class speakenController {
    @Autowired
    speakEnRecordService speakEnService;
    @PostMapping("/speakFeedback")
    public Result getChat(@RequestBody topicReq tpoic_req) {
        try {
            String ans = speakEnService.getSpeakResponce(tpoic_req.getRequirements(), tpoic_req.getTopic());
            return new Result(ans, 200);
        } catch (Exception e) {
            return new Result("发生未知错误：" + e.getMessage(), 500);
        }
    }
    @PostMapping("/speak-upload")
    public Result uploadChat(@RequestBody speakEnPracticeRecord record) {
        try {
            speakEnService.addRecord(record);
            return new Result(record, 200);
        } catch (Exception e) {
            return new Result("发生未知错误：" + e.getMessage(), 500);
        }
    }
    // 查询数据库所有练习记录
    @GetMapping("/findAll")
    public Result getAllRecord() {
        try {
            List<speakEnPracticeRecord> records = speakEnService.getAllrecord();
            return new Result(records, 200);
        } catch (Exception e) {
            return new Result("发生未知错误：" + e.getMessage(), 500);
        }
    }
    // 按照话题查询数据库练习记录
    @GetMapping("/findAll/{topic}")
    public Result getByTopic(@PathVariable String topic){
        try {
            List<speakEnPracticeRecord> record = speakEnService.findByTopic(topic);
            return new Result(record, 200);
        } catch (Exception e) {
            return new Result("发生未知错误：" + e.getMessage(), 500);
        }
    }
}
