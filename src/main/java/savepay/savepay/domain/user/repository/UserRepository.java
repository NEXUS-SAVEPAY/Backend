package savepay.savepay.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import savepay.savepay.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
