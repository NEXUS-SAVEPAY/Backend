package savepay.savepay.domain.discount.converter;

import org.hibernate.Hibernate;
import savepay.savepay.domain.discount.entity.Discount;
import savepay.savepay.domain.payment.entity.Payment;
import savepay.savepay.domain.payment.entity.card.entity.Card;
import savepay.savepay.domain.payment.entity.pay.entity.Pay;

public class DiscountInfoHelper {

    public static String resolveSource(Discount discount) {
        if (discount.getTelecom() != null) return "TELECOM";
        Payment payment = discount.getPayment();
        if (payment != null) {
            Class<?> actualClass = Hibernate.getClass(payment);

            if (Card.class.isAssignableFrom(actualClass)) return "CARD";
            if (Pay.class.isAssignableFrom(actualClass)) return "PAY";
        }
        return "UNKNOWN";
    }
}
