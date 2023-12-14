package com.easyen.easyenglish.controller;

import com.easyen.easyenglish.dto.Result;
import com.easyen.easyenglish.dto.essayGenerate;
import com.easyen.easyenglish.entity.essay;
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
    // 查询数据库所有作文
    @GetMapping("/findAll")
    public Result getAllEssays() {
        try {
            List<essay> essays = essayService.getAllEssays();
            return Result.success(essays);
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }
    // 根据作文号查询作文
    @GetMapping("/findById/{essayId}")
    public Result findByID(@PathVariable Integer essayId) {
        try {
            essay essay = essayService.findByID(essayId);
            return Result.success(essay);
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }

    // 根据用户号查询作文
    @GetMapping("/findByUser/{userId}")
    public Result findByUser(@PathVariable Integer userId) {
        try {
            List<essay> essays = essayService.findByUser(userId);
            return Result.success(essays);
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }

    // 根据作文标题模糊查询作文
    @GetMapping("/findByTitle/{essayTitle}")
    public Result findEssaysByTitle(@PathVariable String essayTitle) {
        try {
            List<essay> essays = essayService.findEssaysByTitle(essayTitle);
            return Result.success(essays);
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }
    // 增加作文，成功返回200
    @PostMapping("/upload")
    public Result addEssay(@RequestBody essay essay) {
        try {
            essayService.addEssay(essay);
            return Result.successCode();
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }
    // 删除作文，成功返回200
    @DeleteMapping("/delete/{essayId}")
    public Result deleteEssay(@PathVariable Integer essayId) {
        try {
            essayService.deleteEssay(essayId);
            return Result.successCode();
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }
    // 更新作文，成功返回200
    @PutMapping("/update")
    public Result updateEssay(@RequestBody essay essay) {
        try {
            essayService.updateEssay(essay);
            return Result.success(essay);
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }
}
