package savepay.savepay.domain.interestbrand.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import savepay.savepay.domain.interestbrand.dto.InterestBrandRequestDto;
import savepay.savepay.domain.interestbrand.dto.InterestBrandResponseDto;
import savepay.savepay.domain.interestbrand.service.InterestBrandService;
import savepay.savepay.domain.user.entity.User;
import savepay.savepay.global.ApiResponse;
import savepay.savepay.global.security.resolver.UserInjection;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/interest/brand")
@Tag(name = "관심 브랜드 API", description = "유저의 관심 브랜드 API")
public class InterestBrandController {

    private final InterestBrandService interestBrandService;

    @PostMapping("/")
    @Operation(summary = "유저의 관심 브랜드 등록 API", description = "유저의 관심 브랜드 등록하는 메서드")
    public ApiResponse<String> createInterestBrand(@UserInjection User user,
                                                   @RequestBody InterestBrandRequestDto.toBrandIdDto request) {
        interestBrandService.createInterestBrand(user, request);
        return ApiResponse.onSuccess("Successfully created interest brand");
    }

    @DeleteMapping("/")
    @Operation(summary = "유저의 관심 브랜드 삭제 API", description = "유저의 관심 브랜드 삭제하는 메서드")
    public ApiResponse<String> deleteInterestBrand(@UserInjection User user,
                                                   @RequestBody InterestBrandRequestDto.toInterestBrandIdDto request) {
        interestBrandService.deleteInterestBrand(user, request);
        return ApiResponse.onSuccess("Successfully deleted interest brand");
    }

    @GetMapping("/")
    @Operation(summary = "유저의 관심 브랜드 리스트 반환 API", description = "유저의 관심 브랜드 리스트를 반환하는 메서드")
    public ApiResponse<List<InterestBrandResponseDto.InterestBrandInfo>> getInterestBrands(@UserInjection User user) {
        return ApiResponse.onSuccess(interestBrandService.getInterestBrands(user));
    }

    @PostMapping("/search")
    @Operation(summary = "유저의 검색한 브랜드 저장 API", description = "유저가 검색한 브랜드를 저장하는 메서드")
    public ApiResponse<String> createSearchBrands(@UserInjection User user,
                                                  @RequestBody InterestBrandRequestDto.toBrandIdDto request) {
        interestBrandService.createSearchBrands(user, request);
        return ApiResponse.onSuccess("Successfully created search brand");
    }

    @GetMapping("/search")
    @Operation(summary = "유저의 브랜드 검색어 리스트 API", description = "유저가 검색한 최근 브랜드 리스트 내역 10개 반환하는 메서드")
    public ApiResponse<List<InterestBrandResponseDto.InterestBrandInfo>> getSearchBrands(@UserInjection User user) {
        return ApiResponse.onSuccess(interestBrandService.getSearchBrands(user));
    }

    @GetMapping("/check")
    @Operation(summary = "사용자 관심브랜드 등록 여부 확인 API",
            description = "사용자가 관심브랜드를 등록했는지 확인하는 메서드")
    public ApiResponse<Boolean> checkInterestBrands(@UserInjection User user) {
        return ApiResponse.onSuccess(interestBrandService.hasInterestBrands(user));
    }
}
