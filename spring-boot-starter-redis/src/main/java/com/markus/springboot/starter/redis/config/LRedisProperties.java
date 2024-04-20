package com.markus.springboot.starter.redis.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: markus
 * @date: 2024/4/19 10:24 PM
 * @Description: redis 配置信息
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@ConfigurationProperties("redis")
public class LRedisProperties {

    private String host; // redis url
    private int port; // redis port

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
