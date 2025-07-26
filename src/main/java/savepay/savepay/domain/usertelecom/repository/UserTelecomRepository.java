package savepay.savepay.domain.usertelecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import savepay.savepay.domain.user.entity.User;
import savepay.savepay.domain.usertelecom.entity.UserTelecom;

import java.util.Optional;

public interface UserTelecomRepository extends JpaRepository<UserTelecom, Long> {

    Optional<UserTelecom> findByUser(User user);
}
