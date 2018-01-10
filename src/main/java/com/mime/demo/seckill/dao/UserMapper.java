package com.mime.demo.seckill.dao;

import com.mime.demo.seckill.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@CacheConfig(cacheNames = "users")
public interface UserMapper {

    /**
     *
     * @param user
     * @return
     */
    @CacheEvict(allEntries=true)
    int insert(User user);

    @Select("select * from user where id =#{id}")
    @Cacheable(key ="#p0")
    User findById(@Param("id") String id);


    @Update("update tb_user set username=#{username} where id=#{id}")
    @CachePut(key = "#p0")
    void updataById(@Param("id")String id, @Param("username")String username);

    @Select("select * from tb_user")
    @Cacheable(key ="#root.methodName")
        //@CachePut(key = "#root.methodName")
    List<User> findAll();

    /**
     * 如果指定为 true，则方法调用后将立即清空所有缓存
     */

    @CacheEvict(key ="#p0",allEntries=true)
    @Delete("delete from tb_user where id=#{id}")
    void deleteById(@Param("id")String id);


}