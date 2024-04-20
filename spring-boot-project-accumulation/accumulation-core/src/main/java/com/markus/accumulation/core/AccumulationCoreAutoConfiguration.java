package com.markus.accumulation.core;

import com.markus.accumulation.core.cache.RedisClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author: markus
 * @date: 2024/4/20 8:45 PM
 * @Description: 配置类
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Configuration
@ComponentScan(basePackages = "com.markus.accumulation.core")
public class AccumulationCoreAutoConfiguration {

    public AccumulationCoreAutoConfiguration(RedisTemplate<String, String> redisTemplate) {
        RedisClient.register(redisTemplate);
    }
}
