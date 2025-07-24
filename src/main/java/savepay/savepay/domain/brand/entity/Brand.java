package savepay.savepay.domain.brand.entity;

import jakarta.persistence.Entity;
import savepay.savepay.domain.common.BaseEntity;

@Entity
public class Brand extends BaseEntity {

    private String name;

    private String brandImage;

    private BrandCategory category; // enum입니다.
}
