package com.hqq.starter.usecustomstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient  //激活Eureka客户端
public class UseCustomStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UseCustomStarterApplication.class, args);
    }

}
