package savepay.savepay.domain.payment.entity.pay.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import savepay.savepay.domain.payment.entity.pay.dto.PayRequestDto;
import savepay.savepay.domain.payment.entity.pay.dto.PayResponseDto;
import savepay.savepay.domain.payment.entity.pay.service.PayService;
import savepay.savepay.global.ApiResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/pays")
public class PayAdminController {

    private final PayService payService;

    @PostMapping(name = "/", consumes = "multipart/form-data")
    public ApiResponse<PayResponseDto.PayRegistryResponseDto> registerPay(@RequestPart(name = "ImageFile", required = false) MultipartFile img,
                                                                          @RequestPart PayRequestDto.PayRegistryDto payRegistryDto) {
        PayResponseDto.PayRegistryResponseDto dto = payService.register(img, payRegistryDto);
        return ApiResponse.onSuccess(dto);
    }

    @GetMapping("/")
    public ApiResponse<PayResponseDto.PayRegistryResponseDto> findPayById(@RequestParam("payId") Long payId) {
        return ApiResponse.onSuccess(payService.findById(payId));
    }

    @GetMapping("/list")
    public ApiResponse<List<PayResponseDto.PayRegistryResponseDto>> findAll() {
        return ApiResponse.onSuccess(payService.findAll());
    }

    @DeleteMapping("/")
    public ApiResponse<String> deletePay(@RequestParam Long payId) {
        payService.deleteById(payId);

        return ApiResponse.onSuccess("Pay is successfully deleted");
    }
}
