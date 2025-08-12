package savepay.savepay.domain.discount.entity;

import jakarta.persistence.*;
import lombok.Getter;
import savepay.savepay.domain.brand.entity.Brand;
import savepay.savepay.domain.common.BaseEntity;
import savepay.savepay.domain.payment.entity.Payment;
import savepay.savepay.domain.telecom.entity.Telecom;
import savepay.savepay.domain.usertelecom.entity.TelecomGrade;

import java.time.LocalDateTime;

@Entity
@Getter
public class Discount extends BaseEntity {

    private Integer discountPercent;

    private LocalDateTime period;

    private String condition;

    private String infoLink;

    private String details; //혜택방법 상세

    private String pointInfo; // 포인트 정보

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "telecom_id")
    private Telecom telecom;

    @Enumerated(EnumType.STRING)
    private TelecomGrade grade;

    @Enumerated(EnumType.STRING)
    private DiscountType type;
}
