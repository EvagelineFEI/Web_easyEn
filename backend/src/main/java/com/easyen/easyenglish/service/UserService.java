package com.easyen.easyenglish.service;

import com.easyen.easyenglish.entity.User;
import org.springframework.mail.SimpleMailMessage;

import java.util.Random;

public interface UserService {
    public User getUserByEmail(String email);

    public void resetPassword(User user);

    public void sendForgetMail(String email);

    public boolean mailVerf(User user, String VerCode);

}
