package com.easyen.easyenglish.controller;
import com.easyen.easyenglish.dto.Result;
import com.easyen.easyenglish.entity.essay;
import com.easyen.easyenglish.entity.speakEnPracticeRecord;
import com.easyen.easyenglish.service.viewprogressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.easyen.easyenglish.util.JwtUtil;
import java.util.List;

@RestController
@RequestMapping("/api/view-progress")
public class viewprogressController {
    @Autowired
    viewprogressService viewService;
    @PostMapping("/speak-num")
    public Result getSpeaknum(@RequestHeader("Authorization") String userJWT){
        Integer user_id = JwtUtil.getUserIdByJWT(userJWT);
        try {
            Integer num = viewService.getSpeaknum(user_id);
            return Result.success(num);
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }
    @PostMapping("/speak-progress")
    public Result getSpeakP(@RequestHeader("Authorization") String userJWT){
        Integer user_id = JwtUtil.getUserIdByJWT(userJWT);
        try {
            List<speakEnPracticeRecord> ls= viewService.getSpeakP(user_id);
            return Result.success(ls);
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }
    @PostMapping("/essay-num")
    public Result getEssaynum(@RequestHeader("Authorization") String userJWT){
        Integer user_id = JwtUtil.getUserIdByJWT(userJWT);
        try {
            Integer num = viewService.getEssaynum(user_id);
            return Result.success(num);
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }
    @PostMapping("/essayprogress/{user_id}")
    public Result getEssayP(@RequestHeader("Authorization") String userJWT){
        Integer user_id = JwtUtil.getUserIdByJWT(userJWT);
        try {
            List<essay> ls= viewService.getEssayP(user_id);
            return Result.success(ls);
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }

}
