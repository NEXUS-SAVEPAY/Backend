package savepay.savepay.domain.brand.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import savepay.savepay.domain.brand.dto.BrandRequestDto;
import savepay.savepay.domain.brand.service.BrandService;
import savepay.savepay.global.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/brand")
public class BrandController {

    private final BrandService brandService;

    @GetMapping("/search")
    public ApiResponse<?> searchBrand(BrandRequestDto.BrandNameRequestDto request) {
            brandService.searchBrand(request);
    }

}
