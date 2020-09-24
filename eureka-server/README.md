# Eureka服务端搭建

### 1、pom.xml中引入相关依赖
> 需要引入spring-cloud-starter-netflix-eureka-server服务端依赖
>和spring cloud 的相关依赖，以下配置中还引入Spring-Security的依赖，
>主要是为给Eureka服务端增加安全认证，当访问Eureka服务端页面时需要输入用户名和密码才能访问。
```properties
<dependencies>
    <!-- eureka服务端依赖 -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
    </dependency>

    <!-- Sring-Security安全框架依赖 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
        <exclusions>
            <exclusion>
                <groupId>org.junit.vintage</groupId>
                <artifactId>junit-vintage-engine</artifactId>
            </exclusion>
        </exclusions>
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
### 2、application.properties文件配置

```properties
# 是否向注册中心注册自己：由于当前应用为注册中心，不需要向注册中心注册自己
eureka.client.register-with-eureka=false
# 是否需要检索服务：由于注册中心的职责就是维护服务实例，不需要去检索服务
eureka.client.fetch-registry=false
# 是否启用保护模式（默认是启用）
eureka.server.enable-self-preservation=true

## Eureka集群master节点配置
# 当前节点（master）指向到其他从节点的Eureka服务地址
#eureka.client.service-url.defaultZone=http://admin:admin123@localhost:8762/eureka/

## 配置认证信息
spring.security.user.name=admin
spring.security.user.password=admin123
```
### 3、安全认证配置
> 如果增加了Spring-Security安全框架后，需要配置以下Bean：
```java
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭csrf
        http.csrf().disable();
        // 支持httpBasic
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
}
```