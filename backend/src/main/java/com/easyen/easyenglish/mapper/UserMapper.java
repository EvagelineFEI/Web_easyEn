package com.easyen.mapper;

import com.easyen.entitty.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert("insert into users (username, password, email, other_info, account_lock_status, errors_count) " +
            "VALUES(#{username}, #{password}, #{email}, #{other_info}, false, 0)")
    int insertUser(String username, String password, String email, String other_info);

    //登录用
    @Select("select * from users where user_id = #{user_id}")
    User getUserByID(Integer user_id);

    @Update("update users set " +
            "username = #{username}, " +
            "password = #{password}, " +
            "email = #{email}, " +
            "other_info = #{other_info}, " +
            "account_lock_status= #{account_lock_status}, " +
            "errors_count = #{errorsCount} " +
            "where user_id = #{user_id}")
    void updateUser(User user);

}
