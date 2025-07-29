package savepay.savepay.domain.interestbrand.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import savepay.savepay.domain.brand.entity.Brand;
import savepay.savepay.domain.interestbrand.entity.InterestBrand;
import savepay.savepay.domain.interestbrand.entity.InterestBrandCategory;
import savepay.savepay.domain.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface InterestBrandRepository extends JpaRepository<InterestBrand, Long> {
    List<InterestBrand> findByUser(User user);

    @Query("SELECT COUNT(ib) > 0 FROM InterestBrand ib WHERE ib.user = :user AND ib.brand = :brand AND ib.category = :category")
    boolean existsByUserAndBrandAndCategory(@Param("user") User user, @Param("brand") Brand brand, @Param("category") InterestBrandCategory category);
}
