package com.easyen.easyenglish.serviceimpl;

import com.easyen.easyenglish.mapper.UserMapper;
import com.easyen.service.RegisterSercive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class RegisterServiceImpl implements RegisterSercive {
    @Autowired
   private UserMapper userMapper;

   public int UserRegister(String username, String password, String email, String other_info){
       return userMapper.insertUser(username, password, email, other_info);
   }

}
