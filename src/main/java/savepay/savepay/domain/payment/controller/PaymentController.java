package savepay.savepay.domain.payment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import savepay.savepay.domain.payment.dto.PaymentResponseDto;
import savepay.savepay.domain.payment.service.PaymentService;
import savepay.savepay.global.ApiResponse;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/{cardId}")
    public ApiResponse<PaymentResponseDto> getPayment(@PathVariable Long cardId) {
        PaymentResponseDto payment = paymentService.getPayment(cardId);
        return ApiResponse.onSuccess(payment);
    }
}