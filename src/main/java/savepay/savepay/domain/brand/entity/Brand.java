package savepay.savepay.domain.brand.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import savepay.savepay.domain.common.BaseEntity;

@Entity
@Getter
public class Brand extends BaseEntity {

    private String name;

    private String brandImage;

    @Enumerated(EnumType.STRING)
    private BrandCategory category; // enum입니다.
}
