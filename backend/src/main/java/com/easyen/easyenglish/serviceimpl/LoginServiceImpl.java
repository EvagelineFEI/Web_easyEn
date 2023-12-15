package com.easyen.easyenglish.serviceimpl;

import com.easyen.easyenglish.entity.User;
import com.easyen.easyenglish.mapper.UserMapper;
import com.easyen.easyenglish.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserMapper userMapper;

    public User UserLogin(String email, String password) {

        User user = userMapper.getUserByEmail(email);

        if (user != null && !user.isAccountLockStatus()){
            if( user.getPassword().equals(password)) {
                // 登录成功，重置错误次数
                user.setErrorsCount(0);
                userMapper.updateUser(user);
                return user;
            } else {
                // 密码错误，增加错误次数
                user.setErrorsCount(user.getErrorsCount() + 1);

                // 检查错误次数是否达到锁定条件
                if (user.getErrorsCount() >= 3) {
                    user.setAccountLockStatus(true);
                }
                userMapper.updateUser(user);
            }
        }
        return null;
    }
    public User getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

}