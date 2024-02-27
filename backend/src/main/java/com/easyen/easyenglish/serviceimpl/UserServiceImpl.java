package com.easyen.easyenglish.serviceimpl;
import com.easyen.easyenglish.entity.User;
import com.easyen.easyenglish.service.UserService;
import com.easyen.easyenglish.util.RedisUtils;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.easyen.easyenglish.mapper.UserMapper;
import com.easyen.easyenglish.entity.User;
import com.easyen.easyenglish.mapper.UserMapper;
import com.easyen.easyenglish.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    //发送验证码邮箱设置
    @Value("${spring.mail.username}")
    private String from;

    @Resource
    private JavaMailSender mailSender;

    @Autowired
    private RedisUtils redisUtils;


    public User getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    public void resetPassword(User user) {
        userMapper.resetPassword(user);}

    public void sendForgetMail(String email) {
        User user = userMapper.getUserByEmail(email);
        if (null != user) {
            String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
            SimpleMailMessage message = new SimpleMailMessage();
            String content = "您的重置密码验证码为：" + checkCode;
            message.setFrom(from);
            message.setTo(email);
            message.setSubject("易学英语邮箱验证码");
            message.setText(content);
            mailSender.send(message);
            //  120秒后验证码过期
            redisUtils.set(email, checkCode, 1200);
        }
    }

    public boolean mailVerf(User user, String VerCode) {

        if (redisUtils != null) {
            // redis获取邮箱对应验证码
            Object value = redisUtils.get(user.getEmail());
            // 没有验证码，错误
            if (value == null || !value.toString().equals(VerCode)) {
                return false;
            } else {
                redisUtils.delete(user.getEmail());
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public void modifyInformation(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public User getSelfById(User user) {
        return userMapper.getUserById(user.getUser_id());
    }

}


