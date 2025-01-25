package kafka.consumer.back_consumer.service;

import kafka.consumer.back_consumer.dto.UserDto;
import kafka.consumer.back_consumer.models.UserMO;
import kafka.consumer.back_consumer.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private final UserRepository repo;

    @KafkaListener(topics = "${spring.kafka.topic.name}", containerFactory = "beanUserContainerFactory", groupId = "${spring.kafka.consumer.user.groupId}")
    public void consumeKafka(UserDto user) {
        log.info("Consumer of user:{}", user);

        repo.save(convertMO(user));


    }

    private UserMO convertMO(UserDto userDto) {
        return UserMO.builder()
                .name(userDto.getName())
                .firstSurname(userDto.getFirstSurname())
                .emailUser(userDto.getEmailUser())
                .atDate(LocalDate.now())
                .build();
    }

}
