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

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    public BrandResponseDto.BrandInfoDto searchBrand(BrandRequestDto.BrandNameRequestDto request) {
        Brand brand = brandRepository.findByName(request.getName())
                .orElseThrow(() -> new GeneralException(ErrorStatus.BRAND_NOT_FOUND));

        return BrandConverter.toBrandInfoDto(brand);
    }
}
