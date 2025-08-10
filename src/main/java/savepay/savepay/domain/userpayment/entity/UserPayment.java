package savepay.savepay.domain.userpayment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import savepay.savepay.domain.common.BaseEntity;
import savepay.savepay.domain.payment.entity.Payment;
import savepay.savepay.domain.user.entity.User;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPayment extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    // 멤버쉽을 payment별로 생성해서 중복 레코드 생성보다 이 구조가 나을 것 같습니다.
    private Boolean isMembership;

    public static UserPayment createUserPayment(User user, Payment payment, Boolean isMembership) {
        return new UserPayment(user, payment, isMembership);
    }

}
