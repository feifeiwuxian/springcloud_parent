package com.wf.controller;

import com.wf.pojo.User;
import com.wf.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    Userservice userservice;
    @Value("${server.port}")
    private String port;

    /**
     * 根据Id查询
     */
    @RequestMapping("/findById")
    public User findById(Integer id) throws InterruptedException {
        Thread.sleep(3000);
        // 手动抛出异常
        if (id ==1) {
            throw new RuntimeException("啊，服务挂掉了");
        }
        User user = userservice.findById(id);
        user.setNote("当前服务端口：" + port);
        return user;
    }

}
