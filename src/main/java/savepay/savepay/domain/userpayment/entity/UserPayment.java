package savepay.savepay.domain.userpayment.entity;

import jakarta.persistence.*;
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

}
