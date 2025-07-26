package savepay.savepay.domain.brand.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import savepay.savepay.domain.brand.entity.Brand;
import java.util.List;

public interface BrandRepository extends CrudRepository<Brand, Long> {
    @Query("SELECT b FROM Brand b WHERE LOWER(b.name) LIKE LOWER(CONCAT('%', :keyword, '%')) ORDER BY b.name ASC")
    List<Brand> searchBrands(@Param("keyword") String keyword);
}
