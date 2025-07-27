package savepay.savepay.domain.payment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("C")
@Getter
@SuperBuilder
@NoArgsConstructor
public class Card extends Payment {

    @Column(nullable = false)
    private String cardName;

    @Column(nullable = false)
    private String company;
}