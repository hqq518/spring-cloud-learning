package com.hqq.consumer.feign.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * 基于Feign调用远程服务客户端
 */
@FeignClient(value = "use-custom-starter")    // 指明当前接口是Feign客户端，并指定调用已注册到Eureka中服务
@RequestMapping("user")
public interface FeignRemoteClient {

    @GetMapping("userName")
    String userName();

    @GetMapping("userAge")
    int userAge();

    @PostMapping("userInfo")
    Map<String, Object> getUserInfo(@RequestBody Map<String, Object> otherInfo);
}
