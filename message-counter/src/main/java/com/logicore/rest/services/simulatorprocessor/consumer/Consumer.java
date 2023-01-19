package com.logicore.rest.services.simulatorprocessor.consumer;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
@Slf4j
public class Consumer {

    private AtomicLong counter = new AtomicLong(0);
    Map<String, String> map = new ConcurrentHashMap<>();

    @KafkaListener(topics = {"messageprocessed"})
    public void onMessageRest(ConsumerRecord<String, String> customerRecord) {
        map.put(customerRecord.key(), customerRecord.value());
        counter.getAndIncrement();
        log.info("Number of kafka messages received: {} and unique message {} ", String.valueOf(counter), String.valueOf(map.size()));
    }
}
