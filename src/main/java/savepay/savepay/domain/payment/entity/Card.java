package savepay.savepay.domain.payment.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("C")
public class Card extends Payment{

    private String cardName;

}
