package com.easyen.easyenglish.mapper;

import com.easyen.easyenglish.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    //注册用
    int insertUser(String username, String password, String email, String other_info);

    //登录用
    User getUserByID(Integer user_id);
    void updateUser(User user);
}
