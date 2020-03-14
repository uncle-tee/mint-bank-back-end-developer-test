package com.bankwithmint.developertest.consumer;


import com.bankwithmint.developertest.service.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

import javax.inject.Named;


@Named
public class KafkaConsumer implements Consumer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @KafkaListener(topics = "com.ng.vela.even.card_verified", groupId = "group_id")
    @Override
    public void consume(String message) {
        logger.info(String.format("#### -> Consumed message -> %s", message));
    }
}
