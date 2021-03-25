package com.wxf.inventory.service.impl;

import com.wxf.inventory.entity.User;
import com.wxf.inventory.mapper.UserMapper;
import com.wxf.inventory.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserInfo() {
        return userMapper.findUserInfo();
    }
}
