package savepay.savepay.domain.payment.entity.pay.dto;

import java.util.List;

public class PayResponseDto {

    public record PayResponseListDto(
            List<PayResponseOneDto> payResponseOneDtoList
    ){}

    public record PayResponseOneDto(
            String payProvider,
            Boolean isMembership
    ){}
}
