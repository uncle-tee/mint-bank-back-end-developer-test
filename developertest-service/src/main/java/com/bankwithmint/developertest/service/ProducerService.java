package com.bankwithmint.developertest.service;

public interface ProducerService {

    void publish(String topic, String message);
}
