package com.markus.accumulation.service.email.service.impl;

import com.markus.accumulation.core.cache.RedisClient;
import com.markus.accumulation.core.util.JsonUtil;
import com.markus.accumulation.service.email.repository.entity.EmailMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author: markus
 * @date: 2024/4/21 9:41 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Component
@Slf4j
public class EmailMessageListener {
    // todo 如果 kafka 在消费消息时出现了异常，是怎么处理的？
    // todo 如果 kafka 消息中出现了脏数据怎么办？
    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${kafka.group.id}", concurrency = "1")
    public void listen(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            try {
                EmailMessage msg = JsonUtil.toObj((String) message.get(), EmailMessage.class);
                // 这里写对接收到的消息的处理逻辑
                // 这里写入到缓存中，待需要时从缓存中读取
                RedisClient.setStr(msg.getEmailId(), msg.getMessage());
                // 手动 ack
                ack.acknowledge();
                log.info("Kafka 消费成功！Topic: " + topic + ", Message: " + msg);
            } catch (Exception e) {
                log.error("Kafka 消费失败！Topic: {}, Message: {}", topic, message.get(), e);
            }
        }
    }
}
