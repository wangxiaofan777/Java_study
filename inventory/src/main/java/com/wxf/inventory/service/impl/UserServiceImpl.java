package com.wxf.inventory.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wxf.inventory.entity.User;
import com.wxf.inventory.mapper.UserMapper;
import com.wxf.inventory.service.RedisService;
import com.wxf.inventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Autowired
    private RedisService redisService;

    @Override
    public User getUserInfo() {
        return userMapper.selectById(1);
    }

    @Override
    public User getCacheUserInfo() {
        User user = this.userMapper.selectById(1);
        this.redisService.set("user_cache_Jack", JSONObject.toJSONString(user));
        return JSONObject.parseObject(this.redisService.get("user_cache_Jack"), User.class);
    }
}
