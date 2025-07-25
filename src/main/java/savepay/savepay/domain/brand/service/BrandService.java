package savepay.savepay.domain.brand.service;

import jakarta.persistence.GeneratedValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import savepay.savepay.domain.brand.dto.BrandRequestDto;
import savepay.savepay.domain.brand.repository.BrandRepository;
import savepay.savepay.global.code.status.ErrorStatus;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    public void searchBrand(BrandRequestDto.BrandNameRequestDto request) {


    }
}
