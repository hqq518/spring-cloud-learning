package com.hqq.starter.customstarter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 租户属性配置类，用于从properties文件中读取配置
 */
@Data
@ConfigurationProperties("custom.starter.user")     //指定配置前缀
public class UserProperties {
    private String name;
    private int age;
}
