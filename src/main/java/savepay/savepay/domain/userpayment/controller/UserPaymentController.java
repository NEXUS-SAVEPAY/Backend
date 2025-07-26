package savepay.savepay.domain.payment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import savepay.savepay.domain.payment.dto.UserPaymentRequestDto;
import savepay.savepay.domain.payment.dto.UserPaymentResponseDto;
import savepay.savepay.domain.payment.service.UserPaymentService;

import java.util.List;

@RestController
@RequestMapping("/user-payments")
@RequiredArgsConstructor
public class UserPaymentController {
    private final UserPaymentService userPaymentService;

    // 회원 결제수단 등록
    @PostMapping
    public UserPaymentResponseDto registerUserPayment(@RequestBody UserPaymentRequestDto dto) {
        return userPaymentService.registerUserPayment(dto);
    }

    // 회원이 등록한 결제수단 리스트 조회
    @GetMapping("/{userId}")
    public List<UserPaymentResponseDto> getUserPayments(@PathVariable Long userId) {
        return userPaymentService.getUserPayments(userId);
    }
}
