package savepay.savepay.domain.payment.entity.pay.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import savepay.savepay.domain.payment.entity.Payment;

@Entity
@Getter
@DiscriminatorValue("P")
public class Pay extends Payment {

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

}
