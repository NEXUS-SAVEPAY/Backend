package savepay.savepay.domain.interestbrand.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import savepay.savepay.domain.interestbrand.dto.InterestBrandRequestDto;
import savepay.savepay.domain.interestbrand.service.InterestBrandService;
import savepay.savepay.global.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/interest/brand")
public class InterestBrandController {

    private final InterestBrandService interestBrandService;

    @PostMapping("/")
    public ApiResponse<?> createInterestBrand(Authentication authentication,
                                              @RequestBody InterestBrandRequestDto.toBrandIdDto request) {
        String email = authentication.getName();
        interestBrandService.createInterestBrand(email, request);
    }
}
