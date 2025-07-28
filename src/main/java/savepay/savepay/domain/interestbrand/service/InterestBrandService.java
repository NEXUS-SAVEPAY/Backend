package savepay.savepay.domain.interestbrand.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import savepay.savepay.domain.brand.entity.Brand;
import savepay.savepay.domain.brand.repository.BrandRepository;
import savepay.savepay.domain.interestbrand.converter.InterestBrandConverter;
import savepay.savepay.domain.interestbrand.dto.InterestBrandRequestDto;
import savepay.savepay.domain.interestbrand.dto.InterestBrandResponseDto;
import savepay.savepay.domain.interestbrand.entity.InterestBrand;
import savepay.savepay.domain.interestbrand.repository.InterestBrandRepository;
import savepay.savepay.domain.user.entity.User;
import savepay.savepay.domain.user.repository.UserRepository;
import savepay.savepay.global.code.status.ErrorStatus;
import savepay.savepay.global.exception.GeneralException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InterestBrandService {

    private final InterestBrandRepository interestBrandRepository;
    private final UserRepository userRepository;
    private final BrandRepository brandRepository;

    @Transactional
    public void createInterestBrand(String email, InterestBrandRequestDto.toBrandIdDto request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.BRAND_NOT_FOUND));

        InterestBrand interestBrand = InterestBrand.builder()
                .brand(brand)
                .user(user)
                .build();

        interestBrandRepository.save(interestBrand);
    }

    @Transactional
    public void deleteInterestBrand(String email, InterestBrandRequestDto.toInterestBrandIdDto request) {
        InterestBrand interestBrand = interestBrandRepository.findById(request.getInterestBrandId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.INTERESTED_BRAND_NOT_FOUND));

        validateUserAccess(email, interestBrand);
        interestBrandRepository.delete(interestBrand);
    }

    @Transactional(readOnly = true)
    public List<InterestBrandResponseDto.InterestBrandInfo> getInterestBrands(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        return interestBrandRepository.findByUser(user)
                .stream()
                .map(InterestBrandConverter::toInterestBrandInfoDto)
                .collect(Collectors.toList());
    }

    private void validateUserAccess(String email, InterestBrand interestBrand) {
        if (!interestBrand.getUser().getEmail().equals(email)) {
            throw new GeneralException(ErrorStatus.FORBIDDEN);
        }
    }
}
