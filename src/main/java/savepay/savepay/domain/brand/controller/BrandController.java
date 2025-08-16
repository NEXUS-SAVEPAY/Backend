package savepay.savepay.domain.brand.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import savepay.savepay.domain.brand.dto.BrandRequestDto;
import savepay.savepay.domain.brand.dto.BrandResponseDto;
import savepay.savepay.domain.brand.service.BrandService;
import savepay.savepay.global.ApiResponse;

import java.util.List;

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

    @PostMapping(consumes = "multipart/form-data")
    @Operation(summary = "브랜드 생성 API", description = "브랜드를 생성하는 메서드")
    public ApiResponse<String> createBrand(@RequestPart(name = "ImageFile", required = false) MultipartFile img,
                                           @RequestPart BrandRequestDto.BrandInfoRequestDto request) {
        brandService.createBrand(img, request);
        return ApiResponse.onSuccess("Successfully created brand");
    }

    @DeleteMapping("/{brandId}")
    @Operation(summary = "브랜드 삭제 API", description = "브랜드를 삭제하는 메서드")
    public ApiResponse<String> deleteBrand(@PathVariable Long brandId) {
        brandService.deleteBrand(brandId);
        return ApiResponse.onSuccess("Successfully deleted brand");
    }
}
