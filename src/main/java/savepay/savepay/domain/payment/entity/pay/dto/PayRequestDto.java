package savepay.savepay.domain.payment.entity.pay.dto;

import savepay.savepay.domain.payment.entity.pay.entity.PaymentType;

import java.util.List;

public class PayRequestDto {

    public record PayRequestListDto(
       List<PayRequestOneDto> payRequestOneDtoList
    ) {}

    public record PayRequestOneDto(
            PaymentType payProvider,
            Boolean isMemberShip
    ) {}
}
