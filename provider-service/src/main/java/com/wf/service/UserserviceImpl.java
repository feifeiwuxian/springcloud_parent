package com.wf.service;

import com.wf.mapper.UserMapper;
import com.wf.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserserviceImpl implements Userservice {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User findById(Integer id) {
        return userMapper.finById(id);
    }
}
