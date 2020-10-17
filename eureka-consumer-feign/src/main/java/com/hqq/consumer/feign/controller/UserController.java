package com.hqq.consumer.feign.controller;

import com.hqq.consumer.feign.api.FeignRemoteClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 控制器，通过Feign客户端调用远程服务
 */
@RestController
@RequestMapping("feign")
public class UserController {

    /**
     * 注入Feign客户端
     */
    @Autowired
    private FeignRemoteClient feignRemoteClient;

    @GetMapping("userName")
    public String getUserName(){
        String username = this.feignRemoteClient.userName();
        if (StringUtils.isNotBlank(username)){
            return username;
        }
        return null;
    }

    @PostMapping("userInfo")
    public Map<String, Object> getUserInfo(@RequestBody Map<String, Object> otherParam){
        Map<String, Object> userInfo = this.feignRemoteClient.getUserInfo(otherParam);
        if (userInfo != null){
            return userInfo;
        }
        return null;
    }

}
