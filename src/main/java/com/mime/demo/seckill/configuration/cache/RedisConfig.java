package com.mime.demo.seckill.configuration.cache;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * redis 缓存
 */
@Configuration
@EnableCaching
@AutoConfigureAfter({StringRedisTemplate.class,RedisTemplate.class})
public class RedisConfig extends CachingConfigurerSupport {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    //自定义缓存key生成策略
    /*
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator(){
            @Override
            public Object generate(Object target, java.lang.reflect.Method method, Object... params) {
                StringBuffer sb = new StringBuffer();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for(Object obj:params){
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }
    */

    /**
     *         cacheManager.setUsePrefix(true);
     *         RedisCachePrefix cachePrefix = new RedisPrefix("prefix");
     *         cacheManager.setCachePrefix(cachePrefix);
     *         整体缓存过期时间
     *         cacheManager.setDefaultExpiration(3600L);
     *         设置缓存过期时间。key和缓存过期时间，单位秒
     *         Map<String, Long> expiresMap = new HashMap<>();
     *         expiresMap.put("user", 1000L);
     *         cacheManager.setExpires(expiresMap)
     * @return
     */
    @Bean
    @Override
    public CacheManager cacheManager() {
        RedisCacheManager cacheManager = new RedisCacheManager(stringRedisTemplate);
        //设置缓存过期时间
        cacheManager.setDefaultExpiration(10000);
        return cacheManager;
    }

    private void setSerializer(RedisTemplate template,boolean isKeySerializer){
        @SuppressWarnings({ "rawtypes", "unchecked" })
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        //value序列化方式
        template.setValueSerializer(jackson2JsonRedisSerializer);
        //key序列化方式
        if (isKeySerializer){template.setKeySerializer(jackson2JsonRedisSerializer);}
    }

    @PostConstruct
    private void init(){
        //设置序列化工具
        setSerializer(stringRedisTemplate,false);
        setSerializer(redisTemplate,true);
        stringRedisTemplate.afterPropertiesSet();
        redisTemplate.afterPropertiesSet();
    }
}
