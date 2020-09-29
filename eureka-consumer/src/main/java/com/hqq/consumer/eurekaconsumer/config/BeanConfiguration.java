package com.hqq.consumer.eurekaconsumer.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfiguration {

    /**
     * 创建RestTemplate对象
     * 并使用Ribbon的客户负载
     * @return
     */
    @Bean
    @LoadBalanced   //激活Ribbon
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
