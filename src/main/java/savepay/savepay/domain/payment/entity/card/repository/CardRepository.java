package savepay.savepay.domain.payment.entity.card.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import savepay.savepay.domain.payment.entity.card.entity.Card;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findFirstByCompanyAndCardNameContaining(String company, String cardName);
}
