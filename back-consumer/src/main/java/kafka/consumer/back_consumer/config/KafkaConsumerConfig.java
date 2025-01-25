package kafka.consumer.back_consumer.config;

import kafka.consumer.back_consumer.dto.UserDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaServer;

    @Bean
    public ConsumerFactory<String, UserDto> consumerFactory() {
        Map<String, Object> propConfig = new HashMap<>();
        propConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        propConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        propConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(propConfig);
    }

    @Bean(name = "beanUserContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, UserDto> userKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, UserDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }


}
