package savepay.savepay.domain.payment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import savepay.savepay.domain.payment.dto.PaymentRequestDto;
import savepay.savepay.domain.payment.dto.PaymentResponseDto;
import savepay.savepay.domain.payment.entity.Payment;
import savepay.savepay.domain.userpayment.entity.UserPayment;
import savepay.savepay.domain.userpayment.repository.*;
import savepay.savepay.domain.payment.repository.PaymentRepository;
import savepay.savepay.domain.userpayment.*;
import savepay.savepay.domain.payment.converter.PaymentConverter;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserPaymentRepository userPaymentRepository;

    /** 결제수단 등록(회원의 결제수단으로 추가) */
    @Transactional
    public PaymentResponseDto registerPayment(Long userId, PaymentRequestDto dto) {
        // 1. 결제수단(Payment)을 생성 및 저장
        Payment payment = PaymentConverter.toEntity(dto);
        Payment saved = paymentRepository.save(payment);

        // 2. 회원-결제수단 연관(UserPayment)에 추가
        UserPayment userPayment = UserPayment.builder()
                .userId(userId)
                .paymentId(saved.getId())
                .build();
        userPaymentRepository.save(userPayment);

        // 3. Dto로 반환
        return PaymentConverter.toDto(saved);
    }

    /** 회원의 결제수단 목록 조회 */
    @Transactional(readOnly = true)
    public List<PaymentResponseDto> getUserPayments(Long userId) {
        // userId로 UserPayment 리스트 조회
        List<UserPayment> userPayments = userPaymentRepository.findByUserId(userId);
        List<Long> paymentIds = userPayments.stream()
                .map(UserPayment::getPaymentId)
                .collect(Collectors.toList());

        // Payment 엔티티 조회 후 DTO 변환
        List<Payment> payments = paymentRepository.findAllById(paymentIds);
        return payments.stream()
                .map(PaymentConverter::toDto)
                .collect(Collectors.toList());
    }

    /** 결제수단 상세 조회 */
    @Transactional(readOnly = true)
    public PaymentResponseDto getPayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 결제수단이 존재하지 않습니다."));
        return PaymentConverter.toDto(payment);
    }

    /** 회원의 결제수단 삭제 */
    @Transactional
    public void deleteUserPayment(Long userId, Long paymentId) {
        // UserPayment에서 삭제
        userPaymentRepository.deleteByUserIdAndPaymentId(userId, paymentId);

    }
}
