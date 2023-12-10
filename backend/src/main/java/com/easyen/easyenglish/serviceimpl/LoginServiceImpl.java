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

    public User UserLogin(Integer userID, String password) {

        User user = userMapper.getUserByID(userID);

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
    public User getUserByID(int user_id) {
        return userMapper.getUserByID(user_id);
    }

}

