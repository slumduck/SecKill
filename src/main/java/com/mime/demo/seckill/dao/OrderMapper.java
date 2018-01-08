package com.mime.demo.seckill.dao;

import com.mime.demo.seckill.entity.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {
    /**
     *
     * @param record
     * @return
     */
    int insert(Order record);
}