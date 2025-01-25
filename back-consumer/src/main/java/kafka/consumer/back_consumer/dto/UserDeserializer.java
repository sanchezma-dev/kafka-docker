package kafka.consumer.back_consumer.dto;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

public class UserDeserializer implements Deserializer<UserRequest> {


    private final JsonDeserializer<UserRequest> jsonDeserializer;

    public UserDeserializer() {
        this.jsonDeserializer = new JsonDeserializer<>(new TypeReference<UserRequest>() {
        });
        this.jsonDeserializer.addTrustedPackages("kafka.producer.back_producer.dto"); // Define trusted packages
    }

    @Override
    public UserRequest deserialize(final String topic, final byte[] data) {
        return this.jsonDeserializer.deserialize(topic, data);
    }

    @Override
    public void close() {
        // Closing resources if needed
        this.jsonDeserializer.close();
    }
}

