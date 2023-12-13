package com.easyen.easyenglish.service;
import com.easyen.easyenglish.entity.User;

public interface LoginService {

    public User UserLogin(Integer user_id, String password);
}
