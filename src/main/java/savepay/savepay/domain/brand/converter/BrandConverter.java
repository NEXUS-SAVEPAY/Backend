package savepay.savepay.domain.brand.converter;

import savepay.savepay.domain.brand.dto.BrandResponseDto;
import savepay.savepay.domain.brand.entity.Brand;

public class BrandConverter {

    public static BrandResponseDto.BrandInfoDto toBrandInfoDto(Brand brand) {
        return BrandResponseDto.BrandInfoDto.builder()
                .id(brand.getId())
                .brandImage(brand.getBrandImage())
                .name(brand.getName())
                .category(brand.getCategory().name())
                .build();
    }
}
