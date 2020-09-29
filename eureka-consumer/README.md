## 基于Ribbon调用Eureka服务

### RestTemplate组合Ribbon使用
> ribbon其实是可以单独使用的（其实现了客户端负载均衡算法）。在Spring Cloud中使用Ribbon会更简单。
> 前提：已经引入Eureka相关依赖（Ribbon的依赖不需要再引入，因为Eureka已经间接引入了），并已配置好
> Eureka相关注册信息（参照use-custom-starte项目工程配置）

***1、配置RestTemplate***
```java
@Configuration
public class BeanConfiguration {

    /**
     * 创建RestTemplate对象
     * 并使用Ribbon的客户负载
     * @return
     */
    @Bean
    @LoadBalanced   //激活Ribbon
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
```
***2、使用RestTemplate***
```java
@RestController
@RequestMapping("userRibbon")
public class UseRibbonController {

    @Resource
    private RestTemplate restTemplate;

    // Eureka上已注册的服务名称
    private String eurekaProvider = "use-custom-starter";

    @GetMapping("userName")
    public String getUserName(){
        String url = "http://" + eurekaProvider + "/user/userName";
        String userName = this.restTemplate.getForObject(url, String.class);
        return userName;
    }

    @GetMapping("userAge")
    public String getUserAge(){
        String url = "http://" + eurekaProvider + "/user/userAge";
        String userName = this.restTemplate.getForObject(url, String.class);
        return userName;
    }

    @GetMapping("userInfo")
    public Map<String, Object> getUserInfo(){
        String url = "http://" + eurekaProvider + "/user/userInfo";
        Map<String, Object> otherInfo = new HashMap<>();
        otherInfo.put("city", "深圳");
        otherInfo.put("school", "广西民族大学");

        Map<String, Object> userInfo = this.restTemplate.postForObject(url, otherInfo, Map.class);
        return userInfo;
    }
}
```