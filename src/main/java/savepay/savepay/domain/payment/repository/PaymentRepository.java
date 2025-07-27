package savepay.savepay.domain.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import savepay.savepay.domain.payment.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}