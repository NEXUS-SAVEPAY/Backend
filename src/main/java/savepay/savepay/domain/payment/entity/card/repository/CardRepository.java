package savepay.savepay.domain.payment.entity.card.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import savepay.savepay.domain.payment.entity.card.entity.Card;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {

    @Query("""
        SELECT c FROM Card c
            WHERE c.company = :company
            AND c.cardName LIKE CONCAT('%', :cardName, '%')
            LIMIT 1
""")
    Optional<Card> searchCard(@Param("card")String company, @Param("cardName")String cardName);

}
