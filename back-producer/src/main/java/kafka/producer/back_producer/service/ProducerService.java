package kafka.producer.back_producer.service;

import kafka.producer.back_producer.dto.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProducerService {

    private final KafkaTemplate<String, UserRequest> kafkaTemplate;

    public ProducerService(KafkaTemplate<String, UserRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    @Value("${spring.kafka.topic.name}")
    private String topicName;


    public void saveUser(UserRequest userRequest) {
        log.info("User to save: {}", userRequest);
        kafkaTemplate.send(topicName, userRequest);
    }


}
