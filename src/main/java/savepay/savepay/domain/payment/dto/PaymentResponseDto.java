package savepay.savepay.domain.payment.dto;

import lombok.Builder;
import lombok.Getter;
import savepay.savepay.domain.payment.entity.PaymentType;

@Getter
@Builder
public class PaymentResponseDto {
    private Long id;
    private Long userId;
    private Long cardId;

    private String company;
    private String cardName;
    private String cardImage;

    private int amount;

    private PaymentType paymentType;
}
