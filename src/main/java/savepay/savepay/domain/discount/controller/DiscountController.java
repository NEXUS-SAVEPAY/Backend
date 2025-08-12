package savepay.savepay.domain.discount.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import savepay.savepay.domain.discount.dto.DiscountResponseDto;
import savepay.savepay.domain.discount.service.DiscountService;
import savepay.savepay.global.ApiResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/discount")
@Tag(name = "혜택 API", description = "유저의 등록수단 관련 혜택 API")
public class DiscountController {

    private final DiscountService discountService;

    //카드 혜택


    //간편 결제 혜택

    //유저의 브랜드 검색에 따른 해당 브랜드와 관련된 통신사 혜택
    @GetMapping("/telecom")
    @Operation(summary = "유저 통신사와 관련된 검색 브랜드 혜택 반환 API", description = "유저의 관심 브랜드 리스트를 반환하는 메서드")
    public ApiResponse<List<DiscountResponseDto.DiscountInfo>> getTelecomDiscount(Authentication authentication) {
        String email = authentication.getName();
        return ApiResponse.onSuccess(discountService.getTelecomDiscount(email));
    }

    //추천 혜택 api

    // 관심브랜드 관련 혜택 api


    //검색 브랜드에 관해 사용자의 등록수단과 관련된 혜택 api(각각 top2씩 반환해야함 )
    //사용자가 등록한 결제수단(카드, 간편결제, 통신사)별 주요 혜택을 빠르게 확인

    // 사용자의 관심브랜드가 없으면 등록수단 혜택 반환 api


}
