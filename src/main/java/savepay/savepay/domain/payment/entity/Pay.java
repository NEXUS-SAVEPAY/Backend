package savepay.savepay.domain.payment.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("P")
public class Pay extends Payment{
    private PaymentType paymentType;

}
