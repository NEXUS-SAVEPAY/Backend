package savepay.savepay.domain.discount.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import savepay.savepay.domain.discount.dto.DiscountRequestDto;
import savepay.savepay.domain.discount.dto.DiscountResponseDto;
import savepay.savepay.domain.discount.service.DiscountQueryService;
import savepay.savepay.domain.discount.service.DiscountService;
import savepay.savepay.domain.user.entity.User;
import savepay.savepay.global.ApiResponse;
import savepay.savepay.global.security.resolver.UserInjection;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/discount")
@Tag(name = "혜택 API", description = "유저의 등록수단 관련 혜택 API")
public class DiscountController {

    private final DiscountService discountService;
    private final DiscountQueryService discountQueryService;

    @PostMapping("/")
    public ApiResponse<String> createDiscount(@RequestBody DiscountRequestDto.toDiscountInfo request) {
        discountService.createDiscount(request);
        return ApiResponse.onSuccess("Successfully created discount");
    }

    @PostMapping("/discounts")
    @Operation(summary = "유저 검색어에 따른 등록수단별 혜택 반환 API", description = "카드/페이/통신사별 혜택 리스트를 모두 반환하는 메서드")
    public ApiResponse<List<DiscountResponseDto.DiscountInfo>> getAllDiscounts(@UserInjection User user,
                                                                               @RequestBody DiscountRequestDto.toBrandNameDto request) {
        return ApiResponse.onSuccess(discountQueryService.getAllDiscounts(user, request));
    }

    @GetMapping("/interest")
    @Operation(summary = "유저 관심사와 관련된 혜택 반환 API", description = "유저 관심사와 관련된 혜택 리스트 반환하는 메서드")
    public ApiResponse<List<DiscountResponseDto.DiscountInfo>> getInterestBrandDiscount(@UserInjection User user) {
        return ApiResponse.onSuccess(discountQueryService.getInterestBrandDiscount(user));
    }

    @GetMapping("/payment")
    @Operation(summary = "사용자의 각 등록수단에 관해 top2씩 브랜드 혜택 반환 API", description = "사용자의 등록수단에 관해 top2씩 브랜드 혜택 반환하는 메서드")
    public ApiResponse<List<DiscountResponseDto.DiscountInfo>> getUserTopDiscounts(@UserInjection User user) {
        return ApiResponse.onSuccess(discountQueryService.getUserTopDiscounts(user));
    }

    @GetMapping("/interest-or-payment")
    @Operation(summary = "사용자의 관심브랜드 또는 등록수단 혜택 반환 API", description = "사용자의 관심브랜드 또는 등록수단 혜택 반환하는 메서드")
    public ApiResponse<List<DiscountResponseDto.DiscountInfo>> getInterestOrPayment(@UserInjection User user) {
        return ApiResponse.onSuccess(discountQueryService.getInterestOrPayment(user));
    }

    @GetMapping("/card")
    @Operation(summary = "사용자의 등록된 카드와 관련된 브랜드 혜택 반환 API", description = "사용자의 카드와 관련된 브랜드 혜택하는 메서드")
    public ApiResponse<List<DiscountResponseDto.DiscountInfo>> getCardDiscounts(@UserInjection User user) {
        return ApiResponse.onSuccess(discountQueryService.getCardDiscounts(user));
    }

    @GetMapping("/pay")
    @Operation(summary = "사용자의 등록된 페이와 관련된 브랜드 혜택 반환 API", description = "사용자의 페이와 관련된 브랜드 혜택하는 메서드")
    public ApiResponse<List<DiscountResponseDto.DiscountInfo>> getPayDiscounts(@UserInjection User user) {
        return ApiResponse.onSuccess(discountQueryService.getPayDiscounts(user));
    }

    @GetMapping("/telecom")
    @Operation(summary = "사용자의 등록된 통신사와 관련된 브랜드 혜택 반환 API", description = "사용자의 통신사와 관련된 브랜드 혜택하는 메서드")
    public ApiResponse<List<DiscountResponseDto.DiscountInfo>> getTelecomDiscounts(@UserInjection User user) {
        return ApiResponse.onSuccess(discountQueryService.getTelecomDiscounts(user));
    }

    @GetMapping("/{discountId}")
    @Operation(summary = "혜택 상세보기 API", description = "특정 혜택의 상세 정보를 반환하는 메서드")
    public ApiResponse<DiscountResponseDto.DiscountInfo> getDiscountInfo(@PathVariable Long discountId) {
        return ApiResponse.onSuccess(discountQueryService.getDiscountInfo(discountId));
    }

    @GetMapping("/recommend")
    public ApiResponse<List<DiscountResponseDto.DiscountInfo>> getRecommendedDiscount(@UserInjection User user) {
        return ApiResponse.onSuccess(discountQueryService.getUserRecommendedDiscount(user));
    }
}
