package com.easyen.easyenglish.controller;


import com.easyen.easyenglish.entity.User;
import com.easyen.easyenglish.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import com.easyen.easyenglish.serviceimpl.LoginServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestParam String email,
                                     @RequestParam String password) {
        Map<String, Object> response = new HashMap<>();

        try {
            User userRes = loginService.UserLogin(email, password);

            if (userRes != null) {
                String token = JwtUtil.createToken(userRes);
                Integer user_id = userRes.getUser_id();

                response.put("code", 200);
                response.put("user_id", user_id);
                response.put("token", token);
                response.put("message", "登录成功");
            } else {
                // 检查账户锁定状态
                User user = loginService.getUserByEmail(email);
                if (user != null && user.isAccountLockStatus()) {
                    response.put("code", 403);
                    response.put("token", "");
                    response.put("message", "账号已被锁定");
                } else {
                    response.put("code", 401);
                    response.put("token", "");
                    response.put("message", "密码错误或账号不存在");
                }
            }
        } catch (Exception e) {
            response.put("code", 500);
            response.put("token", "");
            response.put("message", "服务器内部错误");
            e.printStackTrace();
        }

        return response;
    }

}

