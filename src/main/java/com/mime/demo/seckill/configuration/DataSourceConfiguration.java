package com.mime.demo.seckill.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author SlumDuck
 * @create 2018-01-08 15:02
 * @desc 数据库连接池
 */
@Configuration
public class DataSourceConfiguration {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean()
    public DataSource dataSource(){
        DataSource dataSource = new DruidDataSource();
        return dataSource;
    }
}
