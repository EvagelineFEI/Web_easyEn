package com.easyen.easyenglish.controller;
import com.easyen.easyenglish.dto.Result;
import com.easyen.easyenglish.entity.essay;
import com.easyen.easyenglish.entity.speakEnPracticeRecord;
import com.easyen.easyenglish.service.viewprogressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/view-progress")
public class viewprogressController {
    @Autowired
    viewprogressService viewService;
    @GetMapping("/speak-num/{user_id}")
    public Result getSpeaknum(@PathVariable Integer user_id){
        try {
            Integer num = viewService.getSpeaknum(user_id);
            return new Result(num, 200);
        } catch (Exception e) {
            return new Result("发生未知错误：" + e.getMessage(), 500);
        }
    }
    @GetMapping("/speak-progress/{user_id}")
    public Result getSpeakP(@PathVariable Integer user_id){
        try {
            List<speakEnPracticeRecord> ls= viewService.getSpeakP(user_id);
            return new Result(ls, 200);
        } catch (Exception e) {
            return new Result("发生未知错误：" + e.getMessage(), 500);
        }
    }
    @GetMapping("/essay-num/{user_id}")
    public Result getEssaynum(@PathVariable Integer user_id){
        try {
            Integer num = viewService.getEssaynum(user_id);
            return new Result(num, 200);
        } catch (Exception e) {
            return new Result("发生未知错误：" + e.getMessage(), 500);
        }
    }
    @GetMapping("/essayprogress/{user_id}")
    public Result getEssayP(@PathVariable Integer user_id){
        try {
            List<essay> ls= viewService.getEssayP(user_id);
            return new Result(ls, 200);
        } catch (Exception e) {
            return new Result("发生未知错误：" + e.getMessage(), 500);
        }
    }

}
