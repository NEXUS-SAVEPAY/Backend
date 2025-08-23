package savepay.savepay.domain.payment.entity.pay.dto;

import savepay.savepay.domain.payment.entity.pay.entity.PaymentType;

import java.util.List;

public class PayResponseDto {

    public record PayResponseListDto(
            List<PayResponseOneDto> payResponseOneDtoList
    ){}

    public record PayResponseOneDto(
            String payProvider,
            Boolean isMembership,
            String image,
            String company
    ){}

    public record PayRegistryResponseDto(
            Long id,
            String image,
            String company,
            PaymentType paymentType
    ) {}
}
