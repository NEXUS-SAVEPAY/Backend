package savepay.savepay.domain.userpayment.entity;

import jakarta.persistence.*;
import lombok.*;
import savepay.savepay.domain.common.BaseEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPayment extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private Long paymentId;
}
