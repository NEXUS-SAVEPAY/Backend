package savepay.savepay.domain.userpayment.converter;

import savepay.savepay.domain.userpayment.dto.UserPaymentResponseDto;
import savepay.savepay.domain.userpayment.entity.UserPayment;

public class UserPaymentConverter {

    // UserPayment 엔티티 - UserPaymentResponseDto 변환
    public static UserPaymentResponseDto toDto(UserPayment entity) {
        return UserPaymentResponseDto.builder()
                .id(entity.getId())
                .userId(entity.getUser() != null ? entity.getUser().getId() : null)
                .paymentId(entity.getPayment() != null ? entity.getPayment().getId() : null)
                .build();
    }
}
