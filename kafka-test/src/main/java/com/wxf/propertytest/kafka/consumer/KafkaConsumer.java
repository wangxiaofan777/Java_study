package com.wxf.propertytest.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * kafka消费者
 */
@Component
public class KafkaConsumer {

    @KafkaListener(topics = {"test"})
    public void onMessage(String msg) {
        System.out.println(msg);

    }
}
