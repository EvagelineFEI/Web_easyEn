package com.easyen.easyenglish.controller;

import com.easyen.easyenglish.entity.ApiResult;
import com.easyen.easyenglish.serviceimpl.RegisterServiceImpl;
import com.easyen.easyenglish.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RegisterController {
    @Autowired
    private RegisterServiceImpl registerService;

    @PutMapping("/register")
    public ApiResult register(@RequestParam String username,
                              @RequestParam String password,
                              @RequestParam String email,
                              @RequestParam(required = false) String other_info){

        try {
            Integer user_insert =  registerService.UserRegister(username, password, email, other_info);

            if (user_insert != 0) {
                return ApiResultHandler.buildApiResult(200, "请求成功", null);
            }
            return ApiResultHandler.buildApiResult(400, "请求失败", null);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResultHandler.buildApiResult(500, "服务器内部错误", null);
        }
    }
}

