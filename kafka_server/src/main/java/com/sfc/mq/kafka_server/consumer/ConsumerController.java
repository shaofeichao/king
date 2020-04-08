package com.sfc.mq.kafka_server.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import java.util.Optional;

/**
 * 监听队列
 */
@Component
public class ConsumerController {
    @KafkaListener(topics = {"${topic}"})
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            System.out.println(record);
            System.out.println(message);
        }
    }
}
