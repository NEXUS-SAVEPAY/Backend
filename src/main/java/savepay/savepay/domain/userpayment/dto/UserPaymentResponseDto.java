package savepay.savepay.domain.payment.dto;

import lombok.*;
import savepay.savepay.domain.payment.entity.PaymentType;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPaymentResponseDto {
    private Long userPaymentId;
    private Long userId;
    private Long paymentId;
    private String company;
    private String cardName;
    private String cardImage;
    private PaymentType paymentType;
}
