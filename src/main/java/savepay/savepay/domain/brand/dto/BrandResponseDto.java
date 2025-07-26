package savepay.savepay.domain.brand.dto;

import lombok.Builder;
import lombok.Getter;

public class BrandResponseDto {

    @Getter
    @Builder
    public static class BrandInfoDto {
        Long id;
        String name;
        String brandImage;
        String category;
    }
}
