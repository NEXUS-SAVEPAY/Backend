package savepay.savepay.domain.interestbrand.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import savepay.savepay.domain.interestbrand.entity.InterestBrand;
import savepay.savepay.domain.user.entity.User;

import java.util.List;

public interface InterestBrandRepository extends JpaRepository<InterestBrand, Long> {
    List<InterestBrand> findByUser(User user);
}
