package savepay.savepay.domain.payment.entity.card.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import savepay.savepay.domain.payment.entity.Payment;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Card extends Payment {

    @Column(nullable = false)
    private String cardName;

    protected Card(String image, String company, String cardName) {
        super(image, company);
        this.cardName = cardName;
    }

    public static Card createCard(String image, String company, String cardName) {
        return new Card(image, company, cardName);
    }

}