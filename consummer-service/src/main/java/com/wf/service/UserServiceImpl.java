package com.wf.service;

import com.wf.pojo.User;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    @Override
    public User findById(Integer id) {
        User user = new User();
        user.setUsername("feign客户端，熔断降级");
        return user;
    }
}
