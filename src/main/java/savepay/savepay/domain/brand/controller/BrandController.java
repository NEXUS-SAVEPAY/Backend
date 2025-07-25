package savepay.savepay.domain.brand.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import savepay.savepay.domain.brand.dto.BrandRequestDto;
import savepay.savepay.domain.brand.dto.BrandResponseDto;
import savepay.savepay.domain.brand.service.BrandService;
import savepay.savepay.global.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/brand")
@Tag(name = "브랜드 API", description = "브랜드 검색을 통해 브랜드 정보 조회 API")
public class BrandController {

    private final BrandService brandService;

    @PostMapping("/search")
    @Operation(summary = "브랜드 검색 API", description = "사용자가 브랜드 검색할 때 조회하는 메서드")
    public ApiResponse<BrandResponseDto.BrandInfoDto> searchBrand(@RequestBody BrandRequestDto.BrandNameRequestDto request) {
        return ApiResponse.onSuccess(brandService.searchBrand(request));
    }
}
