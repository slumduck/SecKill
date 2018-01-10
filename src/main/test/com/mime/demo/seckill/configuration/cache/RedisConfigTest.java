package com.mime.demo.seckill.configuration.cache;

import com.mime.demo.seckill.BootStrap;
import com.mime.demo.seckill.BootStrapTest;
import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

/**
 * @author SlumDuck
 * @create 2018-01-09 9:56
 * @desc
 */
public class RedisConfigTest extends BootStrapTest{

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void setRedisTemplateTest(){
        redisTemplate.opsForList().leftPush("redisTemplate","redisTemplate");
    }

    @Test
    public void getRedisTemplateTest(){
        String key = (String) redisTemplate.opsForList().index("redisTemplate",0);
        System.out.println("redisTemplate = " + key);
    }

    @Test
    public void setStringRedisTemplateTest(){
        //stringRedisTemplate.opsForValue().setIfAbsent("stringRedisTemplate","stringRedisTemplate");
        stringRedisTemplate.opsForValue().set("stringRedisTemplate","string_slumduck");
    }

    @Test
    public void getStringRedisTemplateTest(){
        String value = stringRedisTemplate.opsForValue().get("stringRedisTemplate");
        System.out.println("redisTemplate = " + value);
    }

}
