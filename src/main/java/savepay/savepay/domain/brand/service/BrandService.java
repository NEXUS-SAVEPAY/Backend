package savepay.savepay.domain.brand.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
import savepay.savepay.domain.brand.converter.BrandConverter;
import savepay.savepay.domain.brand.dto.BrandRequestDto;
import savepay.savepay.domain.brand.dto.BrandResponseDto;
import savepay.savepay.domain.brand.entity.Brand;
import savepay.savepay.domain.brand.entity.BrandCategory;
import savepay.savepay.domain.brand.repository.BrandRepository;
import savepay.savepay.global.aws.AwsS3Service;
import savepay.savepay.global.code.status.ErrorStatus;
import savepay.savepay.global.exception.GeneralException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;
    private final AwsS3Service awsS3Service;

    public List<BrandResponseDto.BrandInfoDto> searchBrand(BrandRequestDto.BrandNameRequestDto request) {
        List<Brand> brands = brandRepository.searchBrands(request.getName());
        if (brands.isEmpty()) {
            throw new GeneralException(ErrorStatus.BRAND_NOT_FOUND);
        }

        return brands.stream()
                .map(BrandConverter::toBrandInfoDto)
                .toList();
    }

    public void createBrand(MultipartFile img, BrandRequestDto.BrandInfoRequestDto request) {
        String imgUrl = awsS3Service.uploadFile(img);

        Brand brand = Brand.builder()
                .name(request.getName())
                .category(BrandCategory.valueOf(request.getCategory()))
                .brandImage(imgUrl)
                .build();

        brandRepository.save(brand);
    }

    public void deleteBrand(Long brandId) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.BRAND_NOT_FOUND));

        awsS3Service.deleteFile(brand.getBrandImage());
        brandRepository.deleteById(brandId);
    }
}
