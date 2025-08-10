package savepay.savepay.domain.payment.entity.pay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import savepay.savepay.domain.payment.entity.pay.entity.Pay;
import savepay.savepay.domain.payment.entity.pay.entity.PaymentType;

public interface PayRepository extends JpaRepository<Pay, Long> {

    Pay findByPaymentType(PaymentType paymentType);

}
