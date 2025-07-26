package savepay.savepay.domain.payment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import savepay.savepay.domain.payment.entity.PaymentType;

@Getter
@NoArgsConstructor

public class PaymentRequestDto {
    private Long userId; // UserId
    private Long cardId; // CardId
    private int amount; // 결제 요청 시 결제하려는 금액
    private PaymentType paymentType; // 결제 지불 방식
}
