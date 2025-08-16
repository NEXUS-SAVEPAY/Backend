package savepay.savepay.domain.brand.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import savepay.savepay.domain.common.BaseEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Brand extends BaseEntity {

    private String name;

    private String brandImage;

    @Enumerated(EnumType.STRING)
    private BrandCategory category; // enum입니다.

    @Builder
    public Brand(String name, String brandImage, BrandCategory category) {
        this.name = name;
        this.brandImage = brandImage;
        this.category = category;
    }
}
