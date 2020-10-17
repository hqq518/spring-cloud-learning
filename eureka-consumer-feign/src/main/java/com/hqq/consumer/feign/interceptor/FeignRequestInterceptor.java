package com.hqq.consumer.feign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * Feign客户端请求拦截器
 */
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        System.out.println("==> 请求拦截器");
    }
}
