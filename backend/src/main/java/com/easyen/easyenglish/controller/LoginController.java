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

    @PostMapping( "/login")
    public Map<String, Object> login(@RequestParam int user_id,
                                     @RequestParam String password) {

        Map<String, Object> response = new HashMap<>();
        User userRes = loginService.UserLogin(user_id, password);

        if (userRes != null) {
            String token = JwtUtil.createToken(userRes);
            response.put("code", 200);
            response.put("token", token);
            response.put("message", "登录成功");
            return response;

        } else {
            // 检查账户锁定状态
            User user = loginService.getUserByID(user_id);
            if (user != null && user.isAccountLockStatus()) {
                response.put("code", 403);
                response.put("token", "");
                response.put("message", "账号已被锁定");
                return response;
            }
            response.put("code", 401);
            response.put("token", "");
            response.put("message", "密码错误或账号不存在");
            return response;
        }
    }
}

