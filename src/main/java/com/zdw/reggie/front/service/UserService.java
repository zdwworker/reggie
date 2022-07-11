package com.zdw.reggie.front.service;

import com.zdw.reggie.front.domain.User;

public interface UserService {

    User selectByphone(String phone);

    int insertUser(User user);
}
