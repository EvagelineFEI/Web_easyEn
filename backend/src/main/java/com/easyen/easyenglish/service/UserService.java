package com.easyen.easyenglish.service;

import com.easyen.easyenglish.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import java.util.Random;

public interface UserService {
    //修改信息，所以把这些信息封装了传给后台，更改数据库
    void modifyInformation(User user);

    User getSelfById(User user);
    public User getUserByEmail(String email);

    public void resetPassword(User user);

    public void sendForgetMail(String email);

    public boolean mailVerf(User user, String VerCode);

}
