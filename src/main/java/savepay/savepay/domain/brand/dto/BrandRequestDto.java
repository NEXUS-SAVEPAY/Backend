package savepay.savepay.domain.brand.dto;

import lombok.Getter;

public class BrandRequestDto {

    @Getter
    public static class BrandNameRequestDto {
        String name;
    }

    @Getter
    public static class BrandInfoRequestDto {
        String name;
        String category;
    }
}
