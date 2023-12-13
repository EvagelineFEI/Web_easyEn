package com.easyen.easyenglish.service;
import com.easyen.easyenglish.entity.User;

public interface LoginService {

    public User UserLogin(String email, String password);
}
