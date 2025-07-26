package savepay.savepay.domain.payment.converter;

import savepay.savepay.domain.payment.dto.PaymentRequestDto;
import savepay.savepay.domain.payment.dto.PaymentResponseDto;
import savepay.savepay.domain.payment.dto.entity.Payment;

public class PaymentConverter {

    public static Payment toEntity(PaymentRequestDto dto) {
        return Payment.builder()
                .userId(dto.getUserId())
                .cardId(dto.getCardId())
                .amount(dto.getAmount())
                .paymentType(dto.getPaymentType())
                .build();
    }
    public static PaymentResponseDto toDto(Payment entity) {
        return PaymentResponseDto.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .amount(entity.getAmount())
                .paymentType(entity.getPaymentType())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}