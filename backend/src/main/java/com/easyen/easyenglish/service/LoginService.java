package com.easyen.service;
import com.easyen.entitty.User;

public interface LoginService {

    public User UserLogin(Integer user_id, String password);
}
