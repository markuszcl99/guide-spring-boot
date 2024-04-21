package com.markus.accumulation.service.email.service.impl;

import com.markus.accumulation.api.vo.Response;
import com.markus.accumulation.core.cache.RedisClient;
import com.markus.accumulation.core.util.JsonUtil;
import com.markus.accumulation.service.email.repository.entity.EmailMessage;
import com.markus.accumulation.service.email.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author: markus
 * @date: 2024/4/21 9:13 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Service
public class EmailService implements IEmailService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic.name}")
    private String topicName;

    @Override
    public Response<String> sendMessage(String emailId, String message) {
        EmailMessage emailMessage = new EmailMessage(emailId, message);
        String data = JsonUtil.toStr(emailMessage);
        kafkaTemplate.send(topicName, data);
        return Response.ok("发送成功");
    }

    @Override
    public Response<String> readMessage(String emailId) {
        return Response.ok(RedisClient.getStr(emailId));
    }
}
