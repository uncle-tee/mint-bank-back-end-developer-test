package com.bankwithmint.developertest.consumer;


import com.bankwithmint.developertest.service.CardLookupService;
import com.bankwithmint.developertest.service.Consumer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import com.bankwithmint.developertest.binlist.CardLookup;

import javax.inject.Inject;
import javax.inject.Named;


@Named
public class KafkaConsumer implements Consumer {

    @Inject
    CardLookupService cardLookupService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @KafkaListener(topics = "com.ng.vela.even.card_verified", groupId = "group_id")
    @Override
    public void consume(String message) {
        try {
            CardLookup cardLookup = new Gson().fromJson(message, new TypeToken<CardLookup>() {
            }.getType());
            cardLookupService.persistCardLookUp(cardLookup);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("There is a fatal error when trying to persist data");
        }

    }
}
