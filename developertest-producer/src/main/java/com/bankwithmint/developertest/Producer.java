package com.bankwithmint.developertest;


import com.bankwithmint.developertest.service.ProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class Producer implements ProducerService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    final KafkaTemplate<String, String> kafkaTemplate;

    public Producer(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(String topic, String message) {
        System.out.println("Send message here");
        kafkaTemplate.send(topic, message);
    }
}
