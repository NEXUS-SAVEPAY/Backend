package savepay.savepay.domain.payment.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import savepay.savepay.domain.common.BaseEntity;

/*
    단일 테이블 전략
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Entity
public class Payment extends BaseEntity {

    private String image;

    private String company;
}
