package com.markus.accumulation.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author: markus
 * @date: 2024/4/14 3:44 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Configuration
@ComponentScan("com.markus.accumulation.service")
@MapperScan(basePackages = {
        "com.markus.accumulation.service.user.repository.mapper"
})
public class ServiceAutoConfig {
}
