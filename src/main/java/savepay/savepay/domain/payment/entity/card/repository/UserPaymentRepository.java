package savepay.savepay.domain.payment.entity.card.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import savepay.savepay.domain.payment.entity.Payment;
import savepay.savepay.domain.user.entity.User;
import savepay.savepay.domain.userpayment.entity.UserPayment;

import java.util.List;
import java.util.Optional;

public interface UserPaymentRepository extends JpaRepository<UserPayment, Long> {
    // Card까지 한 번에 조회
    @Query("""
            SELECT up FROM UserPayment up
            JOIN FETCH up.payment p
            WHERE TYPE(p) = Card AND up.user = :user
            ORDER BY up.createdAt
""")
    List<UserPayment> findUserCardList(@Param("user")User user);

    @Query("""
            SELECT up FROM UserPayment up
            JOIN FETCH up.payment p
            WHERE TYPE(p) = Pay AND up.user = :user
            ORDER BY up.createdAt
""")
    List<UserPayment> findUserPayList(@Param("user")User user);

    Optional<UserPayment> findByUserAndPayment(User user, Payment payment);

    @Query("""
        DELETE from UserPayment up
        WHERE TYPE(up.payment) = Pay AND up.user = :user
""")
    void deletePayAllByUser(User user);
}
