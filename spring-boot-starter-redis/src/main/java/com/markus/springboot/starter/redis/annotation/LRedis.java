package com.markus.springboot.starter.redis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: markus
 * @date: 2024/4/19 8:47 PM
 * @Description: 自定义 Redis 注解，只起到一个标识作用，用于标记使用了此注解的接口，被扫描代理
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface LRedis {
}
