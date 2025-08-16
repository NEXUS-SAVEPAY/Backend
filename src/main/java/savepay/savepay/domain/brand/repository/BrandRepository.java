package savepay.savepay.domain.brand.repository;

import org.springframework.data.repository.CrudRepository;
import savepay.savepay.domain.brand.entity.Brand;
import java.util.Optional;

public interface BrandRepository extends CrudRepository<Brand, Long> {
    Optional<Brand> findByName(String name);
}
