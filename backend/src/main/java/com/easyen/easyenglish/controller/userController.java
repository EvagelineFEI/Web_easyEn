package com.easyen.easyenglish.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.easyen.easyenglish.dto.Result;
import com.easyen.easyenglish.entity.User;
import com.easyen.easyenglish.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.easyen.easyenglish.util.JwtUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.example.foodiedemo.utils.ImgUtil;
@RestController
@RequestMapping("/api/user")
public class userController {
    @Autowired
    private UserService userService;

    // 用户更新信息
    @PostMapping("/Update")
    public Result Update(@RequestHeader("Authorization") String userJWT,
                         @RequestPart(value = "formData") String jsonData,
                         @RequestParam(value = "name", required = false) MultipartFile image) {
        Integer userId = JwtUtil.getUserIdByJWT(userJWT);
        // 获取更新的信息
        JSONObject jsonObject = JSONObject.parseObject(jsonData);
        User user = JSON.toJavaObject(jsonObject, User.class);
        user.setUser_id(userId);
        // 保存图像到本地
        if (image != null && !image.isEmpty()) {
            try {
                String fileName = ImgUtil.generateRandomString(32);
                user.setUserImg(fileName);
                Path path = Paths.get("./img/" + fileName + ".png");
                Files.copy(image.getInputStream(), path);
                user.setUserImg(ImgUtil.getUrl() + fileName);
            } catch (IOException e) {
                return Result.failure("头像上传失败" + e.getMessage());
            }
        }
        // 保存信息
        try {
            userService.modifyInformation(user);
        } catch (Exception ex) {
            return Result.failure("个人信息修改失败 " + ex.getMessage());
        }
        return Result.success("个人信息修改成功");
    }
    @PostMapping("/QueryUser")
    public Result QueryUser(@RequestHeader("Authorization") String userJWT, @RequestBody User user) {
        Integer userId = JwtUtil.getUserIdByJWT(userJWT);
        User u;
        user.setUser_id(userId);
        try {
            u = userService.getSelfById(user);
            return Result.success(u);
        }catch (Exception e){
            return Result.failure("查询个人信息失败");
        }

    }
}
