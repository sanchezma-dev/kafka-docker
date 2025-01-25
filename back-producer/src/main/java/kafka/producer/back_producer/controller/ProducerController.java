package kafka.producer.back_producer.controller;


import kafka.producer.back_producer.service.ProducerService;
import kafka.producer.back_producer.dto.UserRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/producer/v1")
@AllArgsConstructor
public class ProducerController {

    private final ProducerService producerService;

    @PostMapping("/saveUser")
    public ResponseEntity<String> saveUser(@RequestBody UserRequest userRequest) {
        producerService.saveUser(userRequest);
        return ResponseEntity.ok("User saved with date: " + LocalDate.now());
    }

}
