package savepay.savepay.domain.brand.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import savepay.savepay.domain.common.BaseEntity;

@Entity
@Getter
public class Brand extends BaseEntity {

    private String name;

    private String brandImage;

    private BrandCategory category; // enum입니다.
}
