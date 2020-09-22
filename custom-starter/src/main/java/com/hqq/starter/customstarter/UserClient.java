package com.hqq.starter.customstarter;

/**
 * 获取用户客户端（该对象用于提供给业务代码中注入）
 * 当前类的对象在UserAutoConfigure中被实例化，业务代码中可直接注入当前对象
 */
public class UserClient {

    private UserProperties userProperties;

    public UserClient(){}

    public UserClient(UserProperties properties){
        this.userProperties = properties;
    }

    public String getUserName(){
        return this.userProperties.getName();
    }

    public int getUserAge(){
        return this.userProperties.getAge();
    }

}
