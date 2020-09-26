package com.hqq.eureka.eurekaserver.listener;

import com.netflix.appinfo.InstanceInfo;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Eureka服务状态改变监听器
 */
@Component
public class EurekaStateChangeListener {

    /**
     * 服务下线事件
     * @param event
     */
    @EventListener
    public void listener(EurekaInstanceCanceledEvent event){
        System.err.println("==> " +event.getServerId() + "\t" + event.getAppName() + " 服务下线了 ");
    }

    /**
     * 服务注册事件
     * @param event
     */
    @EventListener
    public void listener(EurekaInstanceRegisteredEvent event){
        InstanceInfo instanceInfo = event.getInstanceInfo();
        System.out.println("==> " + instanceInfo.getAppName() + " 服务进行注册了 ");
    }

    /**
     * 服务续约事件
     * @param event
     */
    @EventListener
    public void listener(EurekaInstanceRenewedEvent event){
        System.out.println("==> " +event.getServerId() + "\t" + event.getAppName() + " 服务进行续约 ");
    }

    /**
     * Eureka注册中心启动事件
     * @param event
     */
    @EventListener
    public void listener(EurekaRegistryAvailableEvent event){
        System.out.println("==> 注册中心启动了");
    }

    /**
     * Eureka Server启动事件
     * @param event
     */
    @EventListener
    public void listener(EurekaServerStartedEvent event){
        System.out.println("==> Eureka Server 启动");
    }
}
