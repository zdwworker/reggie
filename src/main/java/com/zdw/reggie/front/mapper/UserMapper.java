package com.zdw.reggie.front.mapper;

import com.zdw.reggie.front.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User selectByphone(String phone);

    int insertUser(User user);
}
