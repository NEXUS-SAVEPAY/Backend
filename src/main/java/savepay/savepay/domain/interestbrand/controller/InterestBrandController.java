package savepay.savepay.domain.interestbrand.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import savepay.savepay.domain.interestbrand.dto.InterestBrandRequestDto;
import savepay.savepay.domain.interestbrand.dto.InterestBrandResponseDto;
import savepay.savepay.domain.interestbrand.service.InterestBrandService;
import savepay.savepay.global.ApiResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/interest/brand")
public class InterestBrandController {

    private final InterestBrandService interestBrandService;

    @PostMapping("/")
    public ApiResponse<String> createInterestBrand(Authentication authentication,
                                                   @RequestBody InterestBrandRequestDto.toBrandIdDto request) {
        String email = authentication.getName();
        interestBrandService.createInterestBrand(email, request);
        return ApiResponse.onSuccess("Successfully created interest brand");
    }

    @DeleteMapping("/")
    public ApiResponse<String> deleteInterestBrand(Authentication authentication,
                                                   @RequestBody InterestBrandRequestDto.toInterestBrandIdDto request) {
        String email = authentication.getName();
        interestBrandService.deleteInterestBrand(email, request);
        return ApiResponse.onSuccess("Successfully deleted interest brand");
    }

    @GetMapping("/")
    public ApiResponse<List<InterestBrandResponseDto.InterestBrandInfo>> getInterestBrands(Authentication authentication) {
        String email = authentication.getName();
        return ApiResponse.onSuccess(interestBrandService.getInterestBrands(email));
    }
}
