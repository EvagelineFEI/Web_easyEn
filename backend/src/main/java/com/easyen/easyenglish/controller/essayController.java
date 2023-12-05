package com.easyen.easyenglish.controller;

import com.easyen.easyenglish.dto.essayGenerate;
import com.easyen.easyenglish.entity.essay;
import org.springframework.http.ResponseEntity;
import com.easyen.easyenglish.service.essayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/essay")
public class essayController {
    @Autowired
    essayService essayService;

    // 查询数据库所有作文
    @GetMapping("/findAll")
    public ResponseEntity<Object> getAllEssays() {
        List<essay> essays = essayService.getAllEssays();
        if (!essays.isEmpty()) {
            return ResponseEntity.ok(Map.of("code", 200, "essays", essays));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 根据作文号查询作文
    @GetMapping("/findById/{essayId}")
    public ResponseEntity<Object> findByID(@PathVariable Integer essayId) {
        essay essay = essayService.findByID(essayId);
        if (essay != null) {
            return ResponseEntity.ok(Map.of("code", 200, "essay", essay));
        } else {
            return ResponseEntity.ok(Map.of("code", 500, "essay", "[]"));
        }
    }

    // 根据用户号查询作文
    @GetMapping("/findByUser/{userId}")
    public ResponseEntity<Object> findByUser(@PathVariable Integer userId) {
        List<essay> essays = essayService.findByUser(userId);
        if (!essays.isEmpty()) {
            return ResponseEntity.ok(Map.of("code", 200, "essay", essays));
        } else {
            return ResponseEntity.ok(Map.of("code", 500, "essay", essays));
        }
    }

    // 根据作文标题模糊查询作文
    @GetMapping("/findByTitle/{essayTitle}")
    public ResponseEntity<Object> findEssaysByTitle(@PathVariable String essayTitle) {
        List<essay> essays = essayService.findEssaysByTitle(essayTitle);
        if (!essays.isEmpty()) {
            return ResponseEntity.ok(Map.of("code", 200, "essay", essays));
        } else {
            return ResponseEntity.ok(Map.of("code", 500, "essay", essays));
        }
    }

    // 增加作文，成功返回200
    @PostMapping("/upload")
    public ResponseEntity<String> addEssay(@RequestBody essay essay) {
        essayService.addEssay(essay);
        return ResponseEntity.ok("{\"code\": 200}");
    }

    // 删除作文，成功返回200
    @DeleteMapping("/delete/{essayId}")
    public ResponseEntity<String> deleteEssay(@PathVariable Integer essayId) {
        essayService.deleteEssay(essayId);
        return ResponseEntity.ok("{\"code\": 200}");
    }

    // 更新作文，成功返回200
    @PutMapping("/update")
    public ResponseEntity<String> updateEssay(@RequestBody essay essay) {
        essayService.updateEssay(essay);
        return ResponseEntity.ok("{\"code\": 200}");
    }
}
