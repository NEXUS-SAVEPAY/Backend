package savepay.savepay.domain.discount.converter;

import savepay.savepay.domain.discount.entity.Discount;
import savepay.savepay.domain.payment.entity.card.entity.Card;

public class DiscountInfoHelper {

    public static String resolveSource(Discount discount) {
        if (discount.getTelecom() != null) return "TELECOM";
        if (discount.getPayment() instanceof Card) return "CARD";
        if (discount.getPayment() != null) return "PAY";
        return "UNKNOWN";
    }
}
