package savepay.savepay.domain.interestbrand.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "관심 브랜드 API", description = "유저의 관심 브랜드 API")
public class InterestBrandController {

    private final InterestBrandService interestBrandService;

    @PostMapping("/")
    @Operation(summary = "유저의 관심 브랜드 등록 API", description = "유저의 관심 브랜드 등록하는 메서드")
    public ApiResponse<String> createInterestBrand(Authentication authentication,
                                                   @RequestBody InterestBrandRequestDto.toBrandIdDto request) {
        String email = authentication.getName();
        interestBrandService.createInterestBrand(email, request);
        return ApiResponse.onSuccess("Successfully created interest brand");
    }

    @DeleteMapping("/")
    @Operation(summary = "유저의 관심 브랜드 삭제 API", description = "유저의 관심 브랜드 삭제하는 메서드")
    public ApiResponse<String> deleteInterestBrand(Authentication authentication,
                                                   @RequestBody InterestBrandRequestDto.toInterestBrandIdDto request) {
        String email = authentication.getName();
        interestBrandService.deleteInterestBrand(email, request);
        return ApiResponse.onSuccess("Successfully deleted interest brand");
    }

    @GetMapping("/")
    @Operation(summary = "유저의 관심 브랜드 리스트 반환 API", description = "유저의 관심 브랜드 리스트를 반환하는 메서드")
    public ApiResponse<List<InterestBrandResponseDto.InterestBrandInfo>> getInterestBrands(Authentication authentication) {
        String email = authentication.getName();
        return ApiResponse.onSuccess(interestBrandService.getInterestBrands(email));
    }

    @PostMapping("/search")
    @Operation(summary = "유저의 검색한 브랜드 저장 API", description = "유저가 검색한 브랜드를 저장하는 메서드")
    public ApiResponse<String> createSearchBrands(Authentication authentication,
                                                  @RequestBody InterestBrandRequestDto.toBrandIdDto request) {
        String email = authentication.getName();
        interestBrandService.createSearchBrands(email, request);
        return ApiResponse.onSuccess("Successfully created search brand");
    }

    @GetMapping("/search")
    @Operation(summary = "유저의 브랜드 검색어 리스트 API", description = "유저가 검색한 브랜드 리스트 내역을 반환하는 메서드")
    public ApiResponse<List<InterestBrandResponseDto.InterestBrandInfo>> getSearchBrands(Authentication authentication) {
        String email = authentication.getName();
        return ApiResponse.onSuccess(interestBrandService.getSearchBrands(email));
    }
}
