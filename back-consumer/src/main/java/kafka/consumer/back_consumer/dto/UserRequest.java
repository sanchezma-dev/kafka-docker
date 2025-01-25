package kafka.consumer.back_consumer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
public class UserRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private String firstSurname;
    private String emailUser;
}
