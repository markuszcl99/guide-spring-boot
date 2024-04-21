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
    // todo 如果 kafka 在消费消息时出现了异常，业务工厂通常会如何处理？
    // 1、重试机制 : 最常见的处理方式就是实现一个重试机制，当消费者处理消息时出现异常，可以选择将消息重新放回 Kafka 中，或者在内部实现重试逻辑，重新消费该逻辑。通过重试机制，可以尝试多次处理失败的消息，直到成功为止。
    // 2、错误处理 : 业务工程通常会实现一些错误处理逻辑，例如将异常信息记录到日志中，或者将异常消息发送到专门的错误处理队列中。这样可以方便后续排查问题和处理异常数据。
    // 3、暂停消费 : 在某些情况下，如果消费者频繁出现异常导致系统不稳定，可以选择暂停消费，等待问题解决后在恢复消费。这样可以避免异常消息对系统造成更大的影响。
    // 4、监控报警 : 业务工程通常会实现监控系统，监控 Kafka 消费者的健康状态。当消费者出现异常时，可以及时发送报警通知，通知业务运维人员或开发人员进行处理。
    // 5、消息死信队列 : 为了避免因为异常消息导致消费者阻塞，可以使用消息死信队列(DLQ)。当消费者无法处理某条消息时，将该消息发送到死信队列中，以便后续分析和处理。
    // 6、限流降级 : 在消息异常频繁发生时，可以考虑实现限流或者降级，控制消费者的并发处理能力，避免系统过载和崩溃。
    // 只要没有 ack，那么该消息就不会丢失，待修复数据后，还能再次读取

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${kafka.group.id}", concurrency = "1")
    public void listen(ConsumerRecord<String, String> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional<String> message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            try {
                EmailMessage msg = JsonUtil.toObj(message.get(), EmailMessage.class);
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
