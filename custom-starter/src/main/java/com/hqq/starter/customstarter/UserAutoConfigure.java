package com.hqq.starter.customstarter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动装配类（即初始starter所需要的所有bean，以便业务代码中直接可注入使用相关对象）
 */
@Configuration
@EnableConfigurationProperties(UserProperties.class)
public class UserAutoConfigure {

    /**
     * 实例化UserClient对象到Spring容器中
     * 实例化对象条件：当properties文件中配置有spring.user.enabled=true时，才会实例化
     * @param properties
     * @return
     */
    @Bean
    @ConditionalOnProperty(prefix = "custom.starter", value = "enabled", havingValue = "true")
    public UserClient userClient(UserProperties properties){
        return new UserClient(properties);
    }
}
