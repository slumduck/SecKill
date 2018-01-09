package com.mime.demo.seckill.controller;

import com.mime.demo.seckill.dao.UserMapper;
import com.mime.demo.seckill.entity.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author SlumDuck
 * @create 2018-01-09 10:38
 * @desc
 */
@RestController
@RequestMapping("redis")
public class RedisController {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private UserMapper userMapper;

    @RequestMapping("string/set/{key}/{value}")
    public String setValue(@PathVariable ("key") String key,@PathVariable("value") String value){
        return stringRedisTemplate.opsForValue().setIfAbsent(key,value).toString();
    }

    @RequestMapping("string/get/{key}")
    public String getValue(@PathVariable ("key") String key){
        return stringRedisTemplate.opsForValue().get(key);
    }

    @RequestMapping("object/set/{key}/{value}")
    public String setObject(@PathVariable ("key") String key,@PathVariable("value") String value){
        return redisTemplate.opsForValue().setIfAbsent(key,value).toString();
    }

    @RequestMapping("object/get/{key}")
    public String getObject(@PathVariable ("key") String key){
        return (String) redisTemplate.opsForValue().get(key);
    }

    @RequestMapping("string/delete/{key}")
    public void deleteString(@PathVariable ("key") String key){
        stringRedisTemplate.delete(key);
    }

    @RequestMapping("object/delete/{key}")
    public void deleteObject(@PathVariable ("key") String key){
        redisTemplate.delete(key);
    }

    @RequestMapping("cache/findAll")
    public List<User> redisCache(){
        List<User> userList = userMapper.findAll();
        return userList;
    }

    @RequestMapping("test")
    public String test(){
        return "hello world";
    }
}
