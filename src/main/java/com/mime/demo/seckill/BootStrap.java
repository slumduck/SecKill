package com.mime.demo.seckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author SlumDuck
 * @create 2018-01-08 16:30
 * @desc 启动类
 */
@SpringBootApplication
@EnableTransactionManagement
@ComponentScan("com.mime.demo.seckill")
@EnableCaching
@EnableRedisHttpSession
@MapperScan("com.mime.demo.dao")
public class BootStrap {

    public static void main(String[] args) {
        SpringApplication.run(BootStrap.class);
    }
}
