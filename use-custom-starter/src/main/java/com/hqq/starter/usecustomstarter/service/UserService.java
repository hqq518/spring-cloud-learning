package com.hqq.starter.usecustomstarter.service;

import com.hqq.starter.customstarter.UserClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户相关服务类
 */
@Service
public class UserService {

    /**
     * 注入UserClient，该对象在自定义Starter中已被实例化
     */
    @Resource
    private UserClient userClient;

    public String getUserName(){
        return this.userClient.getUserName();
    }

    public int getUserAge(){
        return this.userClient.getUserAge();
    }

}
