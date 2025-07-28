package savepay.savepay.domain.interestbrand.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.http.HttpStatus;
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

    @Builder
    public InterestBrand(User user, Brand brand) {
        this.user = user;
        this.brand = brand;
    }
}
