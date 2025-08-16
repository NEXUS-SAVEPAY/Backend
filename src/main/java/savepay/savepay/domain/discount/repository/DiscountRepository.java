package savepay.savepay.domain.discount.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import savepay.savepay.domain.brand.entity.Brand;
import savepay.savepay.domain.discount.entity.Discount;
import savepay.savepay.domain.payment.entity.Payment;
import savepay.savepay.domain.telecom.entity.Telecom;
import savepay.savepay.domain.usertelecom.entity.TelecomGrade;

import java.time.LocalDateTime;
import java.util.List;

public interface DiscountRepository extends JpaRepository<Discount, Long> {

    @Query("""
                SELECT d
                FROM Discount d
                WHERE d.brand = :brand
                  AND d.telecom = :telecom
                  AND d.grade = :grade
                  AND d.period > :now
            """)
    List<Discount> findTelecomDiscountByBrand(
            @Param("brand") Brand brand,
            @Param("telecom") Telecom telecom,
            @Param("grade") TelecomGrade grade,
            @Param("now") LocalDateTime now
    );

    @Query("""
                SELECT d
                FROM Discount d
                WHERE d.brand IN :brands
                  And d.period > :now
            """)
    List<Discount> findByBrands(
            @Param("brands") List<Brand> brands,
            @Param("now") LocalDateTime now
    );

    @Query("""
                SELECT d
                FROM Discount d
                WHERE d.brand = :brand
                  AND d.payment IN :payments
                  AND d.period > :now
                ORDER BY d.discountPercent DESC
            """)
    List<Discount> findCardDiscountByBrand(
            @Param("brand") Brand brand,
            @Param("payments") List<Payment> payments,
            @Param("now") LocalDateTime now
    );

    @Query("""
                SELECT d
                FROM Discount d
                WHERE d.brand = :brand
                  AND d.payment IN :pays
                  AND d.period > :now
                ORDER BY d.discountPercent DESC
            """)
    List<Discount> findPayDiscountByBrand(
            @Param("brand") Brand brand,
            @Param("pays") List<Payment> pays,
            @Param("now") LocalDateTime now
    );

    @Query("""
                SELECT d
                FROM Discount d
                WHERE d.payment IN :payments
                  AND d.period > :now
                ORDER BY d.discountPercent DESC
            """)
    List<Discount> findCardDiscountsByPayments(
            @Param("payments") List<Payment> payments,
            @Param("now") LocalDateTime now
    );

    @Query("""
                SELECT d
                FROM Discount d
                WHERE d.telecom = :telecom
                  AND d.grade = :grade
                  AND d.period > :now
                ORDER BY d.discountPercent DESC
            """)
    List<Discount> findTelecomDiscounts(
            @Param("telecom") Telecom telecom,
            @Param("grade") TelecomGrade grade,
            @Param("now") LocalDateTime now
    );

    @Query("""
                SELECT d
                FROM Discount d
                WHERE d.payment IN :payments
                  AND d.period > :now
                ORDER BY d.discountPercent DESC
            """)
    List<Discount> findPayDiscountsByPayments(
            @Param("payments") List<Payment> payments,
            @Param("now") LocalDateTime now
    );
}
