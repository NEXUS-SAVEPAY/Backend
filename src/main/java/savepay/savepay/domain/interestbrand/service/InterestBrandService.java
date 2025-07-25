package savepay.savepay.domain.interestbrand.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import savepay.savepay.domain.interestbrand.dto.InterestBrandRequestDto;
import savepay.savepay.domain.interestbrand.repository.InterestBrandRepository;

@Service
@RequiredArgsConstructor
public class InterestBrandService {

    private final InterestBrandRepository interestBrandRepository;

    public void createInterestBrand(String email, InterestBrandRequestDto.toBrandIdDto request) {

    }
}
