package savepay.savepay.domain.interestbrand.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import savepay.savepay.domain.brand.entity.Brand;
import savepay.savepay.domain.brand.repository.BrandRepository;
import savepay.savepay.domain.interestbrand.converter.InterestBrandConverter;
import savepay.savepay.domain.interestbrand.dto.InterestBrandRequestDto;
import savepay.savepay.domain.interestbrand.dto.InterestBrandResponseDto;
import savepay.savepay.domain.interestbrand.entity.InterestBrand;
import savepay.savepay.domain.interestbrand.entity.InterestBrandCategory;
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
    private final BrandRepository brandRepository;

    @Transactional
    public void createInterestBrand(User user, InterestBrandRequestDto.toBrandIdDto request) {
        Brand brand = brandRepository.findByName(request.getBrandName())
                .orElseThrow(() -> new GeneralException(ErrorStatus.BRAND_NOT_FOUND));

        if (interestBrandRepository.isBrandAlreadyMarkedByUser(user, brand, InterestBrandCategory.INTEREST)) {
            throw new GeneralException(ErrorStatus.DUPLICATE_INTEREST_BRAND);
        }

        InterestBrand interestBrand = InterestBrand.builder()
                .brand(brand)
                .category(InterestBrandCategory.INTEREST)
                .user(user)
                .build();

        interestBrandRepository.save(interestBrand);
    }

    @Transactional
    public void deleteInterestBrand(User user, InterestBrandRequestDto.toInterestBrandIdDto request) {
        InterestBrand interestBrand = interestBrandRepository.findById(request.getInterestBrandId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.INTERESTED_BRAND_NOT_FOUND));

        validateUserAccess(user.getEmail(), interestBrand);
        interestBrandRepository.delete(interestBrand);
    }

    @Transactional(readOnly = true)
    public List<InterestBrandResponseDto.InterestBrandInfo> getInterestBrands(User user) {
        return interestBrandRepository.findByUser(user)
                .stream()
                .filter(interestBrand -> interestBrand.getCategory().equals(InterestBrandCategory.INTEREST))
                .map(InterestBrandConverter::toInterestBrandInfoDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void createSearchBrands(User user, InterestBrandRequestDto.toBrandIdDto request) {
        Brand brand = brandRepository.findByName(request.getBrandName())
                .orElseThrow(() -> new GeneralException(ErrorStatus.BRAND_NOT_FOUND));

        if (interestBrandRepository.isBrandAlreadyMarkedByUser(user, brand, InterestBrandCategory.SEARCH)) {
            return;
        }

        InterestBrand interestBrand = InterestBrand.builder()
                .brand(brand)
                .category(InterestBrandCategory.SEARCH)
                .user(user)
                .build();

        interestBrandRepository.save(interestBrand);
    }

    @Transactional(readOnly = true)
    public List<InterestBrandResponseDto.InterestBrandInfo> getSearchBrands(User user) {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id"));

        List<InterestBrand> searchBrands = interestBrandRepository
                .findRecentByUserAndCategory(user, InterestBrandCategory.SEARCH, pageRequest);

        return searchBrands
                .stream()
                .map(InterestBrandConverter::toInterestBrandInfoDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public boolean hasInterestBrands(User user) {
        return interestBrandRepository.findByUser(user)
                .stream()
                .anyMatch(interestBrand -> interestBrand.getCategory().equals(InterestBrandCategory.INTEREST));
    }

    private void validateUserAccess(String email, InterestBrand interestBrand) {
        if (!interestBrand.getUser().getEmail().equals(email)) {
            throw new GeneralException(ErrorStatus.FORBIDDEN);
        }
    }
}
