package com.mime.demo.seckill.service.impl;

import com.mime.demo.seckill.dao.UserMapper;
import com.mime.demo.seckill.entity.User;
import com.mime.demo.seckill.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author SlumDuck
 * @create 2018-01-08 17:00
 * @desc
 */

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public int insertUser(User user) {
        return userMapper.insert(user);
    }
}
