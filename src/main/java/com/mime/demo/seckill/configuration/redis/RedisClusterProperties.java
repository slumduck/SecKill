package com.mime.demo.seckill.configuration.redis;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 作废
 */
@Deprecated
//@Configuration
//@ConfigurationProperties(prefix = "spring.redis.cluster")
public class RedisClusterProperties {

    //集群节点
    private List<String> nodes=new ArrayList<>();

    public List<String> getNodes() {
        return nodes;
    }

    public void setNodes(List<String> nodes) {
        this.nodes = nodes;
    }
}
