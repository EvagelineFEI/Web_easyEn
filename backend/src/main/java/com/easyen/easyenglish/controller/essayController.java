package com.easyen.easyenglish.controller;

import com.easyen.easyenglish.dto.Result;
import com.easyen.easyenglish.dto.essayGenerate;
import com.easyen.easyenglish.entity.comments;
import com.easyen.easyenglish.entity.essay;
import com.easyen.easyenglish.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import com.easyen.easyenglish.service.essayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/essay")
public class essayController {
    @Autowired
    essayService essayService;

    // 根据作文号查询作文
    @GetMapping("/findById/{essayId}")
    public Result findByID(@PathVariable Integer essayId, @RequestHeader("Authorization") String userJWT) {
        try {
            Integer user_id = JwtUtil.getUserIdByJWT(userJWT);
            essay essay = essayService.findByID(essayId);
            return Result.success(essay);
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }

    // 根据当前用户号查询作文
    // 查询本用户练过的所有作文
    @GetMapping("/findByUser")
    public Result findByUser(@RequestHeader("Authorization") String userJWT) {
        try {
            Integer user_id = JwtUtil.getUserIdByJWT(userJWT);
            List<essay> essays = essayService.findByUser(user_id);
            return Result.success(essays);
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }

    // 根据作文标题模糊查询作文
    @GetMapping("/findByTitle")
    public Result findEssaysByTitle(@RequestHeader("Authorization") String userJWT,@RequestBody essay essay) {
        try {
            Integer user_id = JwtUtil.getUserIdByJWT(userJWT);
            essay.setUser_id(user_id);
            List<essay> essays = essayService.findEssaysByTitle(essay);
            return Result.success(essays);
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }
    // 增加作文记录，成功返回200
    @PostMapping("/upload")
    public Result addEssay(@RequestBody essay essay, @RequestHeader("Authorization") String userJWT) {
        try {
            Integer user_id = JwtUtil.getUserIdByJWT(userJWT);
            essay.setUser_id(user_id);
            essay.setStatus("pending");
            essayService.addEssay(essay);
            return Result.successCode();
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }
    // 删除作文，成功返回200
    @DeleteMapping("/delete")
    public Result deleteEssay(@RequestBody essay essay, @RequestHeader("Authorization") String userJWT) {
        Integer userId = JwtUtil.getUserIdByJWT(userJWT);
        if (userId != essay.getUser_id()){
            return Result.failure("非法请求, 你不是该作文的所有者");
        }
        try {
            essayService.deleteEssay(essay.getEssay_id());
            return Result.successCode();
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }
    // 更新作文，成功返回200
    @PutMapping("/update")
    public Result updateEssay(@RequestBody essay essay, @RequestHeader("Authorization") String userJWT) {
        try {
            Integer user_id = JwtUtil.getUserIdByJWT(userJWT);
            essay.setUser_id(user_id);
            essay.setStatus("Modified Done");
            essayService.updateEssay(essay);
            return Result.success(essay);
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }
}