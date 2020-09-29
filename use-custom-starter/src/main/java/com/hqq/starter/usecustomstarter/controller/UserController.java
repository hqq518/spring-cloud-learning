package com.hqq.starter.usecustomstarter.controller;

import com.hqq.starter.usecustomstarter.entity.UserInfo;
import com.hqq.starter.usecustomstarter.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

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

    @PostMapping("userInfo")
    public UserInfo getUserInfo(@RequestBody Map<String, Object> otherInfo){
        UserInfo userInfo = this.userService.getUserInfo();
        userInfo.setOtherInfo(otherInfo);
        return userInfo;
    }
}
