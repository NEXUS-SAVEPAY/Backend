package savepay.savepay.domain.payment.entity.pay.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import savepay.savepay.domain.payment.entity.Payment;

@Entity
@Getter
@DiscriminatorValue("P")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Pay extends Payment {

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    protected Pay(String image, String company, PaymentType paymentType) {
        super(image, company);
        this.paymentType = paymentType;
    }

    public static Pay createPay(String image, String company, PaymentType paymentType) {
        return new Pay(image, company, paymentType);
    }
}
