package savepay.savepay.domain.interestbrand.dto;

import lombok.Builder;
import lombok.Getter;

public class InterestBrandResponseDto {

    @Getter
    @Builder
    public static class InterestBrandInfo {
        Long id;
        String name;
        String brandImage;
        String category;
    }
}
