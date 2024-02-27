package com.easyen.easyenglish.controller;


import com.easyen.easyenglish.dto.Result;
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
    public Result login(@RequestParam String email,
                                     @RequestParam String password) {
        Map<String, Object> response = new HashMap<>();
        int code;
        String message = "";

        try {
            User userRes = loginService.UserLogin(email, password);

            if (userRes != null) {
                String token = JwtUtil.createToken(userRes);
                Integer user_id = userRes.getUser_id();

                response.put("user_id", user_id);
                response.put("token", token);
                return Result.success(response);
            } else {
                // 检查账户锁定状态
                User user = loginService.getUserByEmail(email);
                if (user != null && user.isAccountLockStatus()) {
                    return Result.failure("账号已被锁定");
                } else {
                    return Result.failure("密码错误或账号不存在");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Result.failure("服务器内部错误");
    }

}
