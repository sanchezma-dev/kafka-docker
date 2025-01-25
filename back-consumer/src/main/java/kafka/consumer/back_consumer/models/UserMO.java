package kafka.consumer.back_consumer.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Entity
@Data
@Table(name = "users")
public class UserMO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "FIRST_SURNAME")
    private String firstSurname;

    @Column(name = "EMAIL_USER")
    private String emailUser;

    @Column(name = "AT_DATE")
    private LocalDate atDate;

}
