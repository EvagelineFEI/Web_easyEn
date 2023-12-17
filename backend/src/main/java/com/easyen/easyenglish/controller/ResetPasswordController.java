package com.easyen.easyenglish.controller;

import com.easyen.easyenglish.entity.User;
import com.easyen.easyenglish.serviceimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.easyen.easyenglish.dto.Result;
import com.easyen.easyenglish.serviceimpl.UserServiceImpl;

import java.util.Objects;




@RestController
@RequestMapping("/api")
public class ResetPasswordController {

    @Autowired
    private UserServiceImpl userService;
    // 用户忘记密码 获取邮箱验证码
    @PostMapping("/getCheckCode")
    public Result getForgetCheckCode(@RequestParam String email) {
        User user = userService.getUserByEmail(email);
        System.out.println(email);
        if (user != null) {
            System.out.println(user);
            try {
                userService.sendForgetMail(email);
            } catch (Exception e) {
                return Result.failure("发送验证码失败");
            }
        } else {
            return Result.failure("该邮箱未注册");
        }
        return Result.success("发送验证码成功");
    }

    // 用户忘记密码，重设密码、锁定状态和错误次数
    @PostMapping("/forgetPwd")
    public Result ForgetPasswd(@RequestBody User user, @RequestParam String code) {
        User user_db = userService.getUserByEmail(user.getEmail());
        if(Objects.equals(user_db.getPassword(), user.getPassword())){
            return Result.failure("用户密码与旧密码一致");
        }
        else {
            boolean res_verf = userService.mailVerf(user, code);
            if (res_verf){
                user_db.setPassword(user.getPassword());
                userService.resetPassword(user_db);
            }else{
                return Result.failure("验证码错误");
            }
            return Result.success("密码修改成功");
        }
    }
}
