# 基于Feigin调用注册到Eureka上的服务

>使用 Feign作为调用已注册到Eureka上服务的客户端之前，
>需要同时将当前服务也注册到Eureka上。
>
### 引用Eureka和Feign相关依赖
```xml
<dependencies>
    ...       
    <!-- 引入Feign依赖 -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-openfeign</artifactId>
    </dependency>
    
    <!-- 引入Eureka 客户端依赖 -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>

</dependencies>

<dependencyManagement>
    <dependencies>
        <!-- spring cloud 依赖 -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>${spring-cloud.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

### 配置Eureka和Feign相关配置
```properties
## Eureka客户端相关配置
# Eureka注册中心地址
eureka.client.service-url.defaultZone=http://admin:admin123@localhost:8761/eureka/
# 采用ip注册（默认使用服务名注册，在Eureka服务端注册页面上点击实例时无法跳转到正确的ip地址上）
eureka.instance.prefer-ip-address=true
# 定义实例ID格式
eureka.instance.instance-id=${spring.application.name}:${spring.cloud.client.ip-address}:${server.port}

## Feign相关配置（仅在Feign使用默认的URLConnection作为http客户端时生效）
# 链接超时
feign.client.config.default.connect-timeout=5000
# 读取超时
feign.client.config..default.read-timeout=5000
# 日志等级
feign.client.config.default.logger-level=full
# 拦截器
feign.client.config.default.request-interceptors[0]=com.hqq.consumer.feign.interceptor.FeignRequestInterceptor
```

### 启动类上添加相关注解
```java
@SpringBootApplication
@EnableDiscoveryClient  // 激活Eureka客户端自动注册
@EnableFeignClients     // 激活Feign客户端
public class EurekaConsumerFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerFeignApplication.class, args);
    }

}
```

### 编写Feign客户端接口
> 使用SpringCloud的Feign来调用远程服务时，支持使用springMVC的
>相关注解。

```java
/**
 * 基于Feign调用远程服务客户端
 */
@FeignClient(value = "use-custom-starter")    // 指明当前接口是Feign客户端，并指定调用已注册到Eureka中服务
@RequestMapping("user")
public interface FeignRemoteClient {

    @GetMapping("userName")
    String userName();

    @GetMapping("userAge")
    int userAge();

    @PostMapping("userInfo")
    Map<String, Object> getUserInfo(@RequestBody Map<String, Object> otherInfo);
}
```

### 使用Feign客户端
> Feign客户端接口编写之后，便可以在其他Spring管理的Bean中
>注入当前接口并使用了。

```java
 /**
 * 注入Feign客户端
 */
@Autowired
private FeignRemoteClient feignRemoteClient;
```

## Feign拦截器
> 如果需要在Feign调用远程服务之前，做一些统一配置之类的需求，
>可以通过Feign拦截器实现。
>拦截器需要实现RequestInterceptor接口中的apply()方法，并通过
>properties文件中配置拦截即可。
>
```java
/**
 * Feign客户端请求拦截器
 */
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        System.out.println("==> 请求拦截器");
    }
}
```
之后在properties文件中配置拦截
```properties
feign.client.config.default.request-interceptors[0]=com.hqq.consumer.feign.interceptor.FeignRequestInterceptor
```
