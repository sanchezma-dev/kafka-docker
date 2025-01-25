package kafka.consumer.back_consumer.repository;

import kafka.consumer.back_consumer.models.UserMO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserMO, Long> {

}
