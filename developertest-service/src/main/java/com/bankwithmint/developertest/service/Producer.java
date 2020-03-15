package com.bankwithmint.developertest.service;

public interface Producer {

    void publish(String topic, String message);
}
