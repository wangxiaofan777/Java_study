package com.wxf.inventory.config;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Kafka消息处理器
 *
 * @author WangXiaofan777
 * @since 2021-03-30 22:21:22
 */
@Component
public class KafkaMessageProcessor {

    @KafkaListener(topics = "test")
    public void onMessage(String msg) {
        System.out.println(msg);

    }
}
