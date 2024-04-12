package com.markus.springboot.project.accumuldation.service.impl;

import com.markus.springboot.project.accumuldation.service.EchoService;

/**
 * @author: markus
 * @date: 2024/4/12 11:15 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class DefaultEchoService implements EchoService {
    @Override
    public String echo(String message) {
        throw new RuntimeException("错误信息");
    }
}
