package com.zdw.reggie.front.service.impl;

import com.zdw.reggie.front.domain.User;
import com.zdw.reggie.front.mapper.UserMapper;
import com.zdw.reggie.front.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectByphone(String phone) {
        return userMapper.selectByphone(phone);
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }
}
