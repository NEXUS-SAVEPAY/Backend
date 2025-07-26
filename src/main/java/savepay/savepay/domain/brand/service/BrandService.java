package savepay.savepay.domain.brand.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import savepay.savepay.domain.brand.converter.BrandConverter;
import savepay.savepay.domain.brand.dto.BrandRequestDto;
import savepay.savepay.domain.brand.dto.BrandResponseDto;
import savepay.savepay.domain.brand.entity.Brand;
import savepay.savepay.domain.brand.repository.BrandRepository;
import savepay.savepay.global.code.status.ErrorStatus;
import savepay.savepay.global.exception.GeneralException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    public List<BrandResponseDto.BrandInfoDto> searchBrand(BrandRequestDto.BrandNameRequestDto request) {
        List<Brand> brands = brandRepository.searchBrands(request.getName());
        if (brands.isEmpty()) {
            throw new GeneralException(ErrorStatus.BRAND_NOT_FOUND);
        }

        return brands.stream()
                .map(BrandConverter::toBrandInfoDto)
                .toList();
    }
}
