package savepay.savepay.domain.userpayment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import savepay.savepay.domain.userpayment.entity.UserPayment;
import java.util.List;

public interface UserPaymentRepository extends JpaRepository<UserPayment, Long> {
    // 특정 회원이 가진 결제수단 전체 조회
    List<UserPayment> findByUserId(Long userId);

    // 특정 회원이 가진 결제수단 중, 특정 paymentId만 삭제
    void deleteByUserIdAndPaymentId(Long userId, Long paymentId);
}