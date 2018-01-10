package com.mime.demo.seckill.dao;

import com.mime.demo.seckill.BootStrapTest;
import com.mime.demo.seckill.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.junit.Test;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Commit;

import javax.annotation.Resource;
import java.util.List;



public class UserMapperTest extends BootStrapTest{

    @Resource
    private UserMapper userMapper;

    @Test
    public void insertTest(){

    }

    @Test
    public void findByIdTest(){

    }

    @Test
    public void findAllTest(){
        List<User> userList0 = userMapper.findAll();
    }

    @Test
    @Commit
    public void updataByIdTest(){
        userMapper.updataById("8cd06c78-a39e-4af1-99b9-874aef9f9b1e","hello world");
    }

    @Test
    public void deleteByIdTest(){
        userMapper.deleteById("de4ef2ab-476c-46ea-bcc2-cda3298c11b2");
    }


}