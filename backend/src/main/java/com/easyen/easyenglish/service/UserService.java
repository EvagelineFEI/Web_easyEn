package com.easyen.easyenglish.service;

import com.easyen.easyenglish.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    //修改信息，所以把这些信息封装了传给后台，更改数据库
    void modifyInformation(User user);

    User getSelfById(User user);
}
