package com.wf.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wf.pojo.User;
import com.wf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * 通过Feing客户端发送请求
 */
@RestController
public class FeingConsummerController {

    // 注入Feign客户端的接口
    @Autowired
    private UserService userService;

    @RequestMapping("/feignconsumer/{id}")
    public User consumerSendRequest(@PathVariable("id") Integer id) {

        return userService.findById(id);
    }


}
