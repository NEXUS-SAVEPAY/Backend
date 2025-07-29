package savepay.savepay.domain.interestbrand.dto;

import lombok.Getter;

public class InterestBrandRequestDto {

    @Getter
    public static class toBrandIdDto {
        Long brandId;
    }

    @Getter
    public static class toInterestBrandIdDto {
        Long interestBrandId;
    }
}
