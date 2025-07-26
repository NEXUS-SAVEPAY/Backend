package savepay.savepay.domain.userpayment.converter;

import savepay.savepay.domain.userpayment.dto.UserPaymentRequestDto;
import savepay.savepay.domain.userpayment.dto.UserPaymentResponseDto;
import savepay.savepay.domain.userpayment.entity.UserPayment;

public class UserPaymentConverter {

    // UserPaymentRequestDto - UserPayment 엔티티 변환
    public static UserPayment toEntity(UserPaymentRequestDto dto) {
        return UserPayment.builder()
                .userId(dto.getUserId())
                .paymentId(dto.getPaymentId())
                .build();
    }

    // UserPayment 엔티티 - UserPaymentResponseDto 변환
    public static UserPaymentResponseDto toDto(UserPayment entity) {
        return UserPaymentResponseDto.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .paymentId(entity.getPaymentId())
                .build();
    }
}
