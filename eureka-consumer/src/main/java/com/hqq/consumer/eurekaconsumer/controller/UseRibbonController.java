package com.hqq.consumer.eurekaconsumer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 演示基于Ribbon调用已经注册到Eureka上的服务
 */
@RestController
@RequestMapping("userRibbon")
public class UseRibbonController {

    @Resource
    private RestTemplate restTemplate;

    // Eureka上已注册的服务
    private String eurekaProvider = "use-custom-starter";

    @GetMapping("userName")
    public String getUserName(){
        String url = "http://" + eurekaProvider + "/user/userName";
        String userName = this.restTemplate.getForObject(url, String.class);
        return userName;
    }

    @GetMapping("userAge")
    public String getUserAge(){
        String url = "http://" + eurekaProvider + "/user/userAge";
        String userName = this.restTemplate.getForObject(url, String.class);
        return userName;
    }

    @GetMapping("userInfo")
    public Map<String, Object> getUserInfo(){
        String url = "http://" + eurekaProvider + "/user/userInfo";
        Map<String, Object> otherInfo = new HashMap<>();
        otherInfo.put("city", "深圳");
        otherInfo.put("school", "广西民族大学");

        Map<String, Object> userInfo = this.restTemplate.postForObject(url, otherInfo, Map.class);
        return userInfo;
    }
}
