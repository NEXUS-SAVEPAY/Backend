package savepay.savepay.domain.userpayment.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPaymentRequestDto {
    private Long userId;
    private Long paymentId;
}