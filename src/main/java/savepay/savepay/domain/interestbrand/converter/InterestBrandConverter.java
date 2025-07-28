package savepay.savepay.domain.interestbrand.converter;

import savepay.savepay.domain.interestbrand.dto.InterestBrandResponseDto;
import savepay.savepay.domain.interestbrand.entity.InterestBrand;

public class InterestBrandConverter {

    public static InterestBrandResponseDto.InterestBrandInfo toInterestBrandInfoDto(InterestBrand interestBrand) {
        return InterestBrandResponseDto.InterestBrandInfo.builder()
                .id(interestBrand.getId())
                .name(interestBrand.getBrand().getName())
                .brandImage(interestBrand.getBrand().getBrandImage())
                .category(interestBrand.getBrand().getCategory().name())
                .build();
    }
}
