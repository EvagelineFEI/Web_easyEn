package com.easyen.easyenglish.serviceimpl;

import com.easyen.easyenglish.entity.User;
import com.easyen.easyenglish.mapper.UserMapper;
import com.easyen.easyenglish.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper usermapper;
    @Override
    public void modifyInformation(User user) {
        usermapper.updateUser(user);
    }

    @Override
    public User getSelfById(User user) {
        return usermapper.getUserById(user.getUser_id());
    }
}
