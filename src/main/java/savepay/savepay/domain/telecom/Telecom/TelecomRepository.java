package savepay.savepay.domain.telecom.Telecom;

import org.springframework.data.jpa.repository.JpaRepository;
import savepay.savepay.domain.telecom.entity.Telecom;
import savepay.savepay.domain.telecom.entity.TelecomName;

public interface TelecomRepository extends JpaRepository<Telecom, Long> {

    Telecom findByTelecomName(TelecomName telecomName);
}
