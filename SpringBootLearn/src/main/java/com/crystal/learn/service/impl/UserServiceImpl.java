package com.crystal.learn.service.impl;

import com.crystal.learn.bean.User;
import com.crystal.learn.service.UserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Override
    public User findUser() {
        return new User("张三", "123");
    }
}
