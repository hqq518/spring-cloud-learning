package com.hqq.consumer.feign.config;

import com.hqq.consumer.feign.interceptor.FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Feign配置类
 */
//@Configuration
public class FeignConfiguration {

    /**
     * 配置Feign请求拦截器，也可以在properties文件中配置拦截器
     * @return
     */
    //@Bean
    public FeignRequestInterceptor feignRequestInterceptor(){
        return new FeignRequestInterceptor();
    }
}
