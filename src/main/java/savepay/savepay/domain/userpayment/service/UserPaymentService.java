package savepay.savepay.domain.userpayment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import savepay.savepay.domain.userpayment.converter.UserPaymentConverter;
import savepay.savepay.domain.payment.entity.Payment;
import savepay.savepay.domain.userpayment.dto.UserPaymentRequestDto;
import savepay.savepay.domain.userpayment.dto.UserPaymentResponseDto;
import savepay.savepay.domain.userpayment.entity.UserPayment;
import savepay.savepay.domain.payment.repository.PaymentRepository;
import savepay.savepay.domain.userpayment.repository.UserPaymentRepository;
import savepay.savepay.global.code.status.ErrorStatus;
import savepay.savepay.global.exception.GeneralException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserPaymentService {

    private final UserPaymentRepository userPaymentRepository;
    private final PaymentRepository paymentRepository;

    @Transactional
    public UserPaymentResponseDto registerUserPayment(UserPaymentRequestDto dto) {
        Payment payment = paymentRepository.findById(dto.getPaymentId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.PAYMENT_NOT_FOUND));

        UserPayment userPayment = UserPayment.builder()
                .payment(payment)
                .build();
        UserPayment saved = userPaymentRepository.save(userPayment);
        return UserPaymentConverter.toDto(saved);
    }

    @Transactional(readOnly = true)
    public List<UserPaymentResponseDto> getUserPayments(Long userId) {
        List<UserPayment> list = userPaymentRepository.findByUserId(userId);
        return list.stream().map(UserPaymentConverter::toDto).collect(Collectors.toList());
    }
}
