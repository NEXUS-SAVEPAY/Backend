package savepay.savepay.domain.interestbrand.entity;

import jakarta.persistence.*;
import lombok.*;
import savepay.savepay.domain.brand.entity.Brand;
import savepay.savepay.domain.common.BaseEntity;
import savepay.savepay.domain.user.entity.User;

@Entity
@Getter
@NoArgsConstructor
public class InterestBrand extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Enumerated(EnumType.STRING)
    private InterestBrandCategory category;

    @Builder
    public InterestBrand(User user, Brand brand, InterestBrandCategory category) {
        this.user = user;
        this.brand = brand;
        this.category = category;
    }
}
