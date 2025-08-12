package savepay.savepay.domain.discount.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import savepay.savepay.domain.discount.entity.Discount;
import savepay.savepay.domain.telecom.entity.Telecom;
import savepay.savepay.domain.usertelecom.entity.TelecomGrade;

import java.time.LocalDateTime;
import java.util.List;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
    List<Discount> findByTelecomAndGradeAndPeriodAfter(
            Telecom telecom,
            TelecomGrade grade,
            LocalDateTime now
    );}
