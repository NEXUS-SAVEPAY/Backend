package savepay.savepay.domain.userpay.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import savepay.savepay.domain.userpayment.repository.UserPaymentRepository;
import savepay.savepay.domain.payment.entity.pay.converter.PayConverter;
import savepay.savepay.domain.payment.entity.pay.dto.PayRequestDto;
import savepay.savepay.domain.payment.entity.pay.dto.PayResponseDto;
import savepay.savepay.domain.payment.entity.pay.entity.Pay;
import savepay.savepay.domain.payment.entity.pay.repository.PayRepository;
import savepay.savepay.domain.user.entity.User;
import savepay.savepay.domain.userpayment.entity.UserPayment;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserPayService {

    private final PayRepository payRepository;

    private final UserPaymentRepository userPaymentRepository;


    public PayResponseDto.PayResponseListDto findUserPayList(User user) {
        return PayConverter.toListDto(userPaymentRepository.findUserPayList(user));
    }

    @Transactional
    public void registerUserPay(PayRequestDto.PayRequestListDto payRequestListDto, User user) {
        payRequestListDto.payRequestOneDtoList()
                                .forEach(payRequestOneDto -> {
                                    Pay payProvider = payRepository.findByPaymentType(payRequestOneDto.payProvider());
                                    UserPayment userPayment = UserPayment.createUserPayment(user, payProvider, payRequestOneDto.isMemberShip());
                                    userPaymentRepository.save(userPayment);
                                });
    }

    @Transactional
    public void modifyUserPay(PayRequestDto.PayRequestListDto payRequestListDto, User user) {
        userPaymentRepository.deleteAll(userPaymentRepository.findUserPayList(user)); // 기존 모든 UserPay 삭제 후 재등록합니다.

        payRequestListDto.payRequestOneDtoList()
                .forEach(payRequestOneDto -> {
                    Pay payProvider = payRepository.findByPaymentType(payRequestOneDto.payProvider());
                    UserPayment userPayment = UserPayment.createUserPayment(user, payProvider, payRequestOneDto.isMemberShip());
                    userPaymentRepository.save(userPayment);
                });
    }
}

