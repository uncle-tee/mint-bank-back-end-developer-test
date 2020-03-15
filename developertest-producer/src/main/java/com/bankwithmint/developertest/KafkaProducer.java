package com.bankwithmint.developertest;


import com.bankwithmint.developertest.service.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;

import javax.inject.Named;

@Named
public class KafkaProducer implements Producer {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
                                 KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(String topic, String message) {
        kafkaTemplate.send(topic, message);
        this.kafkaTemplate.send(topic, message);


    }
}
