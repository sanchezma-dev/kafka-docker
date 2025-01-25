package kafka.producer.back_producer.config;

import kafka.producer.back_producer.dto.UserRequest;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class UserConfigProducer {

    // kafka server properties
    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaServer;

    // add custom bean properties
    @Bean
    public ProducerFactory<String, UserRequest> producerFactory() {
        Map<String, Object> configProducer = new HashMap<>();
        configProducer.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        configProducer.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProducer.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(configProducer);
    }

    @Bean
    public KafkaTemplate<String, UserRequest> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }


}
