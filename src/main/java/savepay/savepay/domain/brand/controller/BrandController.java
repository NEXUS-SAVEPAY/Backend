package savepay.savepay.domain.brand.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import savepay.savepay.domain.brand.dto.BrandRequestDto;
import savepay.savepay.domain.brand.dto.BrandResponseDto;
import savepay.savepay.domain.brand.service.BrandService;
import savepay.savepay.global.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/brand")
public class BrandController {

    private final BrandService brandService;

    @GetMapping("/search")
    public ApiResponse<BrandResponseDto.BrandInfoDto> searchBrand(BrandRequestDto.BrandNameRequestDto request) {
        return ApiResponse.onSuccess(brandService.searchBrand(request));
    }
}
