package com.mime.demo.seckill.service.impl;

import com.mime.demo.seckill.BootStrapTest;
import com.mime.demo.seckill.dao.UserMapper;
import com.mime.demo.seckill.entity.User;
import org.junit.Test;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author SlumDuck
 * @create 2018-01-08 17:02
 * @desc
 */
public class UserServiceImplTest extends BootStrapTest {

    @Resource
    private UserMapper userMapper;

    //@Rollback
    //@Commit
    @Test
    public void insertUser(){

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername("SlumDuck");
        user.setPassword("@zl334459");
        userMapper.insert(user);

    }
}
