package com.mime.demo.seckill;

import com.mime.demo.seckill.dao.UserMapper;
import com.mime.demo.seckill.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author SlumDuck
 * @create 2018-01-08 16:54
 * @desc test
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BootStrapTest {
    @Resource
    private UserMapper userMapper;

    //@Rollback
    @Test
    public void insertUser(){

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername("SlumDuck");
        user.setPassword("@zl334459");
        userMapper.insert(user);

    }
}
