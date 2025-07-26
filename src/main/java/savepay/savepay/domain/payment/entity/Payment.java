package savepay.savepay.domain.payment.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import savepay.savepay.domain.common.BaseEntity;

/*
    단일 테이블 전략을 사용하기 위해 추상 객체로 설정했습니다.
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Entity
public class Payment extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType; // 결제 수단 타입

    private String cardimage;
    private String cardName;
    private String company;
}
