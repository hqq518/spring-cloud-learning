package com.hqq.starter.usecustomstarter.controller;

import com.hqq.starter.usecustomstarter.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户相关控制器
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("userName")
    public String userName(){
        return this.userService.getUserName();
    }

    @GetMapping("userAge")
    public int userAge(){
        return this.userService.getUserAge();
    }
}
