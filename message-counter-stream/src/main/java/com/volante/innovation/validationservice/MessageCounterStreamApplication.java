package com.volante.innovation.validationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@Configuration
@EnableKafka
public class MessageCounterStreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageCounterStreamApplication.class, args);
	}
}
