### 自定义Srpingboot的Starter
> 自定义Starter需要以下几步：

1、定义properties文件的配置类（根据业务需要）

```java
@Data
@ConfigurationProperties("custom.starter.user")     //指定配置前缀
public class UserProperties {
    private String name;
    private int age;
}
```

2、创建需要提供给业务代码使用的客户端类（如UserClient）
> 该类不需要任何注解，最终该类由自动配置类中实例化到容器中。
```java
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
```

3、创建自动装配类（最重要），该类完成自定义Starter中需要的实例化的所有bean。
```java
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
```

4、注册自动装配类到spring.factories文件中
> 首先在resource目录下创建META-INF目录，然后在MATE-INF中创建spring.factories文件，
> 并在文件里指定自动装配类的全路径。
>

```properties
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
  com.hqq.starter.customstarter.UserAutoConfigure
```

>注意：如果我们不想在业务项目pom.xml中引入自定义Starter后就执行初始化逻辑，
>而是由用户来指定是否要开启自定义Starter的自动装配功能，则可以自定义注解@EnableUserClient,
>此时就不需要编写spring.factories文件了。用户在项目的启动类上添加@EnableUserClient注解即可。
```java
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
```

5、创建properties文件内容提示（根据业务需要）
>当我们在业务项目中引入自定义的Starter后，在propeties中配置自定义Starter的相关配置项时，
>需要idea能够自动提示有哪些配置项以及默认的配置项值是什么时，我们需要在自定义Starter的
>resource/META-INF/目录中创建spring-configuration-metadata.json文件，并在文件中添加
>类似如下内容：

```json
{
  "properties": [
    {
      "name": "custom.starter.user.name",
      "defaultValue": ""
    },
    {
      "name": "custom.starter.user.age",
      "defaultValue": ""
    },
    {
      "name": "custom.starter.enabled",
      "type": "java.lang.Boolean",
      "defaultValue": false
    }
  ]
}
```

### 如何使用自定义Starter

1、在业务项目的pom.xml中直接引入自定义的Starter：
```xml
<!-- 引入自定义Starter -->
<dependency>
    <groupId>com.hqq.starter</groupId>
    <artifactId>custom-starter</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```
2、在业务代码中直接注入自定义Starter提供的Bean
```java
@Service
public class UserService {

    /**
     * 注入UserClient，该对象在自定义Starter中已被实例化
     */
    @Resource
    private UserClient userClient;

    public String getUserName(){
        return this.userClient.getUserName();
    }

    public int getUserAge(){
        return this.userClient.getUserAge();
    }

}
```

3、在业务项目的properties文件中配置自定义Starter相关配置
```properties
## 自定义Starter配置
custom.starter.enabled=true
custom.starter.user.name=huangqiqin
custom.starter.user.age=30
```
