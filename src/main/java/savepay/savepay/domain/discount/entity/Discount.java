package savepay.savepay.domain.discount.entity;

import jakarta.persistence.*;
import savepay.savepay.domain.brand.entity.Brand;
import savepay.savepay.domain.common.BaseEntity;
import savepay.savepay.domain.payment.entity.Payment;

import java.time.LocalDateTime;

@Entity
public class Discount extends BaseEntity {

    private Integer discountPercent; // 다른 타입이 적합하다 생각하시면 변경해주세요

    /*
        일단 시간까지 표기 가능한 타입으로 했습니다.
        나중에 날짜까지만 필요하다고 하면 논의 거쳐서 바꾸면 될 거 같습니다.
     */
    private LocalDateTime period;

    /*
        추가 객체가 필요할지 vs 단순 String으로 할지
     */
    @Column(name = "discount_condition")
    private String condition;

    private String infoLink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private Payment payment;
}
