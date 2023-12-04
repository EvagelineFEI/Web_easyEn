package com.easyen.easyenglish.controller;

import com.easyen.easyenglish.dto.essayGenerate;
import com.easyen.easyenglish.entity.essay;
import com.easyen.easyenglish.service.essayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/essay")
public class essayController {
    @Autowired
    essayService essayService;

    // 查询数据库所有作文
    @GetMapping("/findAll")
    public List<essay> getAllEssays() {
        return essayService.getAllEssays();
    }

    // 根据作文号查询作文
    @GetMapping("/findById/{essayId}")
    public essay findByID(@PathVariable Integer essayId) {
        return essayService.findByID(essayId);
    }

    // 根据用户号查询作文
    @GetMapping("/findByUser/{userId}")
    public List<essay> findByUser(@PathVariable Integer userId) {
        return essayService.findByUser(userId);
    }

    // 根据作文标题模糊查询作文
    @GetMapping("/findByTitle/{essayTitle}")
    public List<essay> findEssaysByTitle(@PathVariable String essayTitle) {
        return essayService.findEssaysByTitle(essayTitle);
    }

    // 增加作文，成功返回200
    @PostMapping("/upload")
    public void addEssay(@RequestBody essay essay) {
        essayService.addEssay(essay);
    }

    // 删除作文，成功返回200
    @DeleteMapping("/delete/{essayId}")
    public int deleteEssay(@PathVariable Integer essayId) {
        essayService.deleteEssay(essayId);
        return 200;
    }

    // 更新作文，成功返回200
    @PutMapping("/update")
    public int updateEssay(@RequestBody essay essay) {
        essayService.updateEssay(essay);
        return 200;
    }
}
