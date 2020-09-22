package com.hqq.starter.customstarter;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用UserAutoConfigure自动装配注解（使用当前注解时，则不需要编写spring.factories文件了）
 * 用户可根据业务需要是否在项目初始化时需要启动当前自定义的Starter，
 * 将该注解标注在项目启动类上，表明启用自定义Starter，否则就算pom.xml中已引入当前自定义的Starter也不会实例
 * 化任何Bean
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({UserAutoConfigure.class})  //该注解将UserAutoConfigure自动装配类导入，实现自动装配
public @interface EnableUserClient {
}
