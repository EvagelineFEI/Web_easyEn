package com.easyen.easyenglish.controller;

import com.easyen.easyenglish.dto.Result;
import com.easyen.easyenglish.entity.ApiResult;
import com.easyen.easyenglish.serviceimpl.RegisterServiceImpl;
import com.easyen.easyenglish.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RegisterController {
    @Autowired
    private RegisterServiceImpl registerService;

    @PostMapping("/register")
    public Result register(@RequestParam String username,
                              @RequestParam String password,
                              @RequestParam String email,
                              @RequestParam(required = false) String other_info){

        try {
            Integer user_insert =  registerService.UserRegister(username, password, email, other_info);

            if (user_insert != 0) {
                return Result.success("请求成功");
                //return ApiResultHandler.buildApiResult(200, "请求成功", null);
            }
            return Result.failure( "请求失败");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure( "请求失败");
        }
    }
}

