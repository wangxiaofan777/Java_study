package com.wxf.inventory.controller;

import com.wxf.inventory.entity.User;
import com.wxf.inventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUserInfo")
    @ResponseBody
    public User getUserInfo() {
        User user = userService.getUserInfo();
        return user;
    }

    @GetMapping("/getCacheUserInfo")
    @ResponseBody
    public User getCacheUserInfo() {
        User user = userService.getCacheUserInfo();
        return user;
    }
}
