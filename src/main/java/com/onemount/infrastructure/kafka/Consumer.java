package com.onemount.infrastructure.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Consumer {
    /**
     * Consuming messages from kafka
     */
    @KafkaListener(topics = "om-hack2hire-booking-accepted", groupId = "om-hack2hire-booking-accepted")
    public void consume(String message) {
        log.info(String.format("#### -> Consumed message -> %s", message));
    }
}
