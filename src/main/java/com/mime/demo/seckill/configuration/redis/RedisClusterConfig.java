package com.mime.demo.seckill.configuration.redis;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 不需要自己实现。。。。
 * spring boot会根据配置实例化redis
 *
 * 			JedisPoolConfig poolConfig = this.properties.getPool() != null
 *                                       ? jedisPoolConfig() : new JedisPoolConfig();
 *          if (getSentinelConfig() != null) {
 *              return new JedisConnectionFactory(getSentinelConfig(), poolConfig);
 *          }
 *          if (getClusterConfiguration() != null) {
 *              return new JedisConnectionFactory(getClusterConfiguration(), poolConfig);
 *          }
 *          return new JedisConnectionFactory(poolConfig);
 * @see RedisAutoConfiguration
 */
//@Configuration
//@EnableConfigurationProperties(RedisClusterProperties.class)
//@EnableConfigurationProperties(RedisProperties.class)
@Deprecated
public class RedisClusterConfig {

    //@Resource
    //private RedisClusterProperties redisClusterProperties;
    @Resource
    private RedisProperties redisProperties;

    @Bean
    public JedisCluster redisCluster(){

        Set<HostAndPort> nodes = new HashSet<>();
        for (String node: redisProperties.getCluster().getNodes()){
            String[] parts= StringUtils.split(node,":");
            Assert.state(parts.length==2, "redis node shoule be defined as 'host:port', not '" + Arrays.toString(parts) + "'");
            nodes.add(new HostAndPort(parts[0], Integer.valueOf(parts[1])));
        }
        return new JedisCluster(nodes,redisProperties.getTimeout(),redisProperties.getCluster().getMaxRedirects());
    }
}
