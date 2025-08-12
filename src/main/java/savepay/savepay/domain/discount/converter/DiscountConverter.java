package savepay.savepay.domain.discount.converter;

import savepay.savepay.domain.discount.dto.DiscountResponseDto;
import savepay.savepay.domain.discount.entity.Discount;

public class DiscountConverter {

    public static DiscountResponseDto.DiscountInfo toDiscountInfoDto(Discount discount) {
        return DiscountResponseDto.DiscountInfo.builder()
                .id(discount.getId())
                .brandImage(discount.getBrand().getBrandImage())
                .brandName(discount.getBrand().getName())
                .discountPercent(discount.getDiscountPercent())
                .discountType(discount.getType().toString())
                .infoLink(discount.getInfoLink())
                .pointInfo(discount.getPointInfo())
                .details(discount.getDetails())
                .build();
    }
}
