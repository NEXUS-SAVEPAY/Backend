package savepay.savepay.domain.payment.entity.pay.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import savepay.savepay.domain.payment.entity.pay.dto.PayRequestDto;
import savepay.savepay.domain.payment.entity.pay.dto.PayResponseDto;
import savepay.savepay.domain.payment.entity.pay.service.PayService;
import savepay.savepay.domain.userpay.service.UserPayService;
import savepay.savepay.domain.user.entity.User;
import savepay.savepay.global.ApiResponse;
import savepay.savepay.global.security.resolver.UserInjection;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pays")
public class PayController {

    private final UserPayService userPayService;

    private final PayService payService;

    @GetMapping("/user")
    public ApiResponse<PayResponseDto.PayResponseListDto> findPay(@UserInjection User user) {
        return ApiResponse.onSuccess(userPayService.findUserPayList(user));
    }

    @PostMapping("/user")
    public ApiResponse<String> postPay(@RequestBody PayRequestDto.PayRequestListDto payRequestListDto,
                                       @UserInjection User user) {
        userPayService.registerUserPay(payRequestListDto, user);
        return ApiResponse.onSuccess("User's pays are successfully registered");
    }

    @PutMapping("/user")
    public ApiResponse<String> modifyPay(@RequestBody PayRequestDto.PayRequestListDto payRequestListDto,
                                         @UserInjection User user) {
        userPayService.modifyUserPay(payRequestListDto, user);
        return ApiResponse.onSuccess("User's card is successfully deleted");
    }
}
