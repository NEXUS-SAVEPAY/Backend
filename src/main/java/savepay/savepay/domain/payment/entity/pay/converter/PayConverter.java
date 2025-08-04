package savepay.savepay.domain.payment.entity.pay.converter;

import savepay.savepay.domain.payment.entity.pay.dto.PayResponseDto;
import savepay.savepay.domain.payment.entity.pay.entity.Pay;
import savepay.savepay.domain.userpayment.entity.UserPayment;

import java.util.List;
import java.util.stream.Collectors;

public class PayConverter {

    public static PayResponseDto.PayResponseListDto toListDto(List<UserPayment> userPaymentList) {
        return new PayResponseDto.PayResponseListDto(userPaymentList.stream()
                .map(up -> toDto((Pay) up.getPayment(), up.getIsMembership()))
                .collect(Collectors.toList()));
    }

    public static PayResponseDto.PayResponseOneDto toDto(Pay pay, Boolean isMembership) {
        return new PayResponseDto.PayResponseOneDto(pay.getPaymentType().toString(),
                isMembership);
    }
}
