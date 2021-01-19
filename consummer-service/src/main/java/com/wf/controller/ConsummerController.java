package com.wf.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wf.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@DefaultProperties(defaultFallback = "defaultFallBackMethod") // 全局服务降级
public class ConsummerController {

    @Autowired
    private RestTemplate restTemplate;

    //    @RequestMapping("/consumer/{id}")
//    public User consumerSendRequest(@PathVariable("id") Integer id) {
//        String url = "http://localhost:9091/user/findById?id=" + id;
//        User user = restTemplate.getForObject(url, User.class);
//        return user;
//    }

    // 缓存着服务注册列表
    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 通过注册中心的注册列表，访问服务提供者。实现url地址host和port动态获取
     * @param id
     * @return
     */
    @RequestMapping("/consumer/{id}")
    public User consumerSendRequest(@PathVariable("id") Integer id) {
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("user-service");
        ServiceInstance userService = serviceInstanceList.get(0);
        String host = userService.getHost();
        int port = userService.getPort();
        Map<String, String> metadata = userService.getMetadata();
        System.out.println("当前userServiece服务的元数据: "+metadata);

        String url = "http://"+host+":"+port+"/user/findById?id=" + id;
        User user = restTemplate.getForObject(url, User.class);
        return user;
    }

    @RequestMapping("/rebbonconsumer/{id}")
//    @HystrixCommand(fallbackMethod = "rebbonFindByIdFallBack") //每个单独定义熔断方法
    @HystrixCommand
    public User rebbonFindById(@PathVariable("id") Integer id) throws InterruptedException {

        // 负载均衡
        String url = "http://user-service/user/findById?id=" + id;
        User user = restTemplate.getForObject(url, User.class);
        return user;
    }
    /**
     * 编写服务降级方法
     * 与被降级的方法，参数，与返回值必须保持一致
     */
    public User rebbonFindByIdFallBack(Integer id) {
        User user = new User();
        user.setUsername("您好，非常抱歉，您访问的用户信息不存在");
        return user;
    }

    /**
     * 全局熔断方法
     */
    public User defaultFallBackMethod() {
        User user = new User();
        user.setUsername("全局默认降级方法：您好，您访问的数据不存在");
        return user;
    }


}
