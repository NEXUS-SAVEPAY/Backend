package savepay.savepay.global.security.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import savepay.savepay.domain.user.entity.User;
import savepay.savepay.global.security.domain.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByUser(User user);

}
