package com.mime.demo.seckill.configuration;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author SlumDuck
 * @create 2018-01-08 15:19
 * @desc mybatis 注解扫描
 */
@Configuration
@AutoConfigureAfter(MybatisConfiguration.class)
public class MybatisMapperScanConfiguration {


    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.mime.demo.seckill.dao");
        return mapperScannerConfigurer;
    }
}
