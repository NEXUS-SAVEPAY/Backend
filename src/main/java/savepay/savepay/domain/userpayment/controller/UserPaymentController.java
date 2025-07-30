package savepay.savepay.domain.userpayment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import savepay.savepay.domain.userpayment.dto.UserPaymentRequestDto;
import savepay.savepay.domain.userpayment.dto.UserPaymentResponseDto;
import savepay.savepay.domain.userpayment.service.UserPaymentService;
import savepay.savepay.global.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/user-payments")
@RequiredArgsConstructor
public class UserPaymentController {
    private final UserPaymentService userPaymentService;

    // 회원 결제수단 등록
    @PostMapping
    public ApiResponse<UserPaymentResponseDto> registerUserPayment(@RequestBody UserPaymentRequestDto dto) {
        UserPaymentResponseDto response = userPaymentService.registerUserPayment(dto);
        return ApiResponse.onSuccess(response);
    }

    // 회원이 등록한 결제수단 리스트 조회
    @GetMapping("/{userId}")
    public ApiResponse<List<UserPaymentResponseDto>> getUserPayments(@PathVariable Long userId) {
        List<UserPaymentResponseDto> responseList = userPaymentService.getUserPayments(userId);
        return ApiResponse.onSuccess(responseList);
    }
}