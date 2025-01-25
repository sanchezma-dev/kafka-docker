package kafka.consumer.back_consumer.config;

import kafka.consumer.back_consumer.dto.UserDeserializer;
import kafka.consumer.back_consumer.dto.UserRequest;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaServer;

    @Bean
    public ConsumerFactory<String, UserRequest> consumerFactory() {
        Map<String, Object> propConfig = new HashMap<>();
        propConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        propConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        // Custom deserializer UserDeserializer
        propConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, UserDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(propConfig);
    }

    @Bean(name = "beanUserContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, UserRequest> userKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, UserRequest> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

}
