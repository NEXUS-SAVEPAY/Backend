package savepay.savepay.domain.telecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import savepay.savepay.domain.telecom.entity.Telecom;
import savepay.savepay.domain.telecom.entity.TelecomName;

public interface TelecomRepository extends JpaRepository<Telecom, Long> {

    Telecom findByTelecomName(TelecomName telecomName);
}
