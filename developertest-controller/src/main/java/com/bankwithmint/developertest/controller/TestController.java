package com.bankwithmint.developertest.controller;

import com.bankwithmint.developertest.response.Response;
import com.bankwithmint.developertest.service.Producer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController()
@RequestMapping("test")
public class TestController {

    final
    Producer producer;

    public TestController(Producer producer) {
        this.producer = producer;
    }


    @GetMapping("kafka/{message}")
    public ResponseEntity<?> testPibSub(@PathVariable String message) {
        producer.publish("TEST_KAFKA", message);
        return ResponseEntity.ok().build();
    }
}
