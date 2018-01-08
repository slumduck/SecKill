package com.mime.demo.seckill.dao;

import com.mime.demo.seckill.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;


@Repository
@CacheConfig(cacheNames = "users")
public interface UserMapper {

    /**
     *
     * @param user
     * @return
     */
    @CachePut(key = "#p0")
    int insert(User user);

    //@Select("select * from user where id =#{id}")
    @Cacheable(key ="#p0")
    User findById(@Param("id") String id);

    @CachePut(key = "#p0")
    //@Update("update user set name=#{name} where id=#{id}")
    void updataById(@Param("id")String id, @Param("name")String name);

    //如果指定为 true，则方法调用后将立即清空所有缓存
    @CacheEvict(key ="#p0",allEntries=true)
    //@Delete("delete from user where id=#{id}")
    void deleteById(@Param("id")String id);


}