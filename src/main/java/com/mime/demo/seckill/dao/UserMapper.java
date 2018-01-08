package com.mime.demo.seckill.dao;

import com.mime.demo.seckill.entity.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper {

    /**
     *
     * @param user
     * @return
     */
    int insert(User user);


}