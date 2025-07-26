package savepay.savepay.domain.payment.dto;

import lombok.Builder;
import lombok.Getter;
import savepay.savepay.domain.payment.entity.PaymentType;

import java.time.LocalDateTime;

@Getter
@Builder

public class PaymentResponseDto {
    private Long cardId;
    private String company;
    private String cardName;
    private String cardImage;
    private PaymentType paymentType;
}
