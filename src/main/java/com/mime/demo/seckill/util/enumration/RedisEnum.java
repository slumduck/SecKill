package com.mime.demo.seckill.util.enumration;

import java.io.Serializable;

/**
 * 设置存入redis中的key前缀
 */
public enum RedisEnum implements Serializable{

    /**
     * cookie，session
     * 共享session
     */
    SESSION("session_","session共享"),
    /**
     * cache
     * 缓存
     */
    CACHE("cache_","缓存"),
    /**
     * queue
     * 队列，秒杀场景
     */
    QUEUE("seckill_","秒杀队列");

    private String key_prefix;
    private String description;

    RedisEnum(String key_prefix, String description) {
        this.key_prefix = key_prefix;
        this.description = description;
    }

    public String getKey_prefix() {
        return key_prefix;
    }

    public void setKey_prefix(String key_prefix) {
        this.key_prefix = key_prefix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
