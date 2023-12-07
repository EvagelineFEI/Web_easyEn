package com.easyen.easyenglish.controller;

import com.easyen.entitty.ApiResult;
import com.easyen.serviceimpl.RegisterServiceImpl;
import com.easyen.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    @Autowired
    private RegisterServiceImpl registerService;

    @PutMapping("/register")
    public ApiResult register(@RequestParam String username,
                              @RequestParam String password,
                              @RequestParam String email,
                              @RequestParam String other_info){

        Integer user_insert =  registerService.UserRegister(username, password, email, other_info);

        System.out.println(user_insert);
        if (user_insert != 0) {

            return ApiResultHandler.buildApiResult(200, "请求成功", null);
        }
        return ApiResultHandler.buildApiResult(400, "请求失败", null);

    }

}
