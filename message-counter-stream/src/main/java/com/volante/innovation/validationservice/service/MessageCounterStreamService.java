package com.volante.innovation.validationservice.service;


import com.volante.innovation.validationservice.bindings.KafkaListenerBinding;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@EnableBinding(KafkaListenerBinding.class)
public class MessageCounterStreamService {

    @StreamListener("input-channel")
//    @SendTo("output-channel")
    public void process(KStream<String, String> input) {

        input.foreach((k, v) -> log.info("We have a message in counter stream: {} {}", k, v));
        input.groupByKey().count().toStream().foreach((key, value) -> log.info("Event {} and count {}", key, value));

    }

}
