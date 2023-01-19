package com.volante.innovation.validationservice.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.volante.innovation.validationservice.Transform.Transform;
import com.volante.innovation.validationservice.bindings.KafkaListenerBinding;
import lombok.extern.slf4j.Slf4j;
import model.PaymentMessage;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@EnableBinding(KafkaListenerBinding.class)
public class ValidationService {

    @StreamListener("input-channel")
    @SendTo("output-channel")
    public KStream<String, String> process(KStream<String, PaymentMessage> input) {

        ObjectMapper objectMapper = new ObjectMapper();
        KStream<String, String> inputTransformed = input.mapValues(v -> {
            try {
                return objectMapper.writeValueAsString(Transform.process(v));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
        inputTransformed.foreach((k, v) -> log.info("We have a message in validate stream: {} {}", k, v));

        return inputTransformed;
    }
}
