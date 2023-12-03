package com.easyen.easyenglish.controller;

import com.easyen.easyenglish.dto.essayGenerate;
import com.easyen.easyenglish.entity.essay;
import com.easyen.easyenglish.service.essayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class essayController {
    @Autowired
    essayService essayService;

    @GetMapping("/findAll")
    public List<essay> getAllEssays() {
        return essayService.getAllEssays();
    }

    @PostMapping("/chat")
    public String getChatResponse(@RequestBody essayGenerate essayGenerate){
        return essayService.generateEssay(essayGenerate.getRequirements(),essayGenerate.getOriginalEssay());
    }
}
