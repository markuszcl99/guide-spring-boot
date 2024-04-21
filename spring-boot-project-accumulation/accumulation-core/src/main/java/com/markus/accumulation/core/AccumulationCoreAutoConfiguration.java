package com.markus.accumulation.core;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.markus.accumulation.core.cache.RedisClient;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author: markus
 * @date: 2024/4/20 8:45 PM
 * @Description: 配置类
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Configuration
@ComponentScan(basePackages = "com.markus.accumulation.core")
@EnableCaching
public class AccumulationCoreAutoConfiguration {

    public AccumulationCoreAutoConfiguration(RedisTemplate<String, String> redisTemplate) {
        RedisClient.register(redisTemplate);
    }

    /**
     * Spring 整合缓存（屏蔽底层，设计统一抽象 API）- 我们这里通过 caffeine 来实现本地缓存，并且配合 Spring @CahceXXX 来使用
     *
     * @Cacheable : 缓存存在，则直接使用缓存，若不存在，则执行方法，并将结果写入缓存
     * @CacheEvit : 失效缓存
     * @CachePut : 更新缓存
     */
    @Bean("caffeineCacheManager")
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder()
                // 设置过期时间，缓存写入后 5 分钟过期
                .expireAfterWrite(5, TimeUnit.MINUTES)
                // 初始化缓存数量（预初始化，避免缓存不断的进行扩容，导致效率不高）
                .initialCapacity(100)
                // 最大缓存条数
                .maximumSize(200));
        return cacheManager;
    }



}
