package savepay.savepay.domain.brand.repository;

import org.springframework.data.repository.CrudRepository;
import savepay.savepay.domain.brand.entity.Brand;
import java.util.List;

public interface BrandRepository extends CrudRepository<Brand, Long> {
    List<Brand> findTop10ByNameContainingIgnoreCaseOrderByNameAsc(String keyword);
}
