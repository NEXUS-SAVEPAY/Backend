package savepay.savepay.domain.payment.converter;

import savepay.savepay.domain.payment.dto.PaymentRequestDto;
import savepay.savepay.domain.payment.dto.PaymentResponseDto;
import savepay.savepay.domain.payment.entity.Payment;

public class PaymentConverter {

    public static Payment toEntity(PaymentRequestDto dto) {
        return Payment.builder()
                .cardId(dto.getCardId())
                .amount(dto.getAmount())
                .paymentType(dto.getPaymentType())
                .build();
    }
    public static PaymentResponseDto toDto(Payment entity) {
        return PaymentResponseDto.builder()
                .id(entity.getId())
                .amount(entity.getAmount())
                .paymentType(entity.getPaymentType())
                .build();
    }
}