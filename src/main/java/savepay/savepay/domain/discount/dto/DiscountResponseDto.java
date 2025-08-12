package savepay.savepay.domain.discount.dto;

import lombok.Builder;
import lombok.Getter;

public class DiscountResponseDto {

    @Getter
    @Builder
    public static class DiscountInfo {
        Long id;
        String brandName;
        String brandImage;
        Integer discountPercent;
        String discountType;
        String infoLink;
        String details; //혜택방법 상세
        String pointInfo; // 포인트 정보
    }
}
