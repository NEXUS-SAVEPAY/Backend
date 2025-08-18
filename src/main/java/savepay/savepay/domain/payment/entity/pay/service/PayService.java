package savepay.savepay.domain.payment.entity.pay.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import savepay.savepay.domain.payment.entity.pay.converter.PayConverter;
import savepay.savepay.domain.payment.entity.pay.dto.PayRequestDto;
import savepay.savepay.domain.payment.entity.pay.dto.PayResponseDto;
import savepay.savepay.domain.payment.entity.pay.entity.Pay;
import savepay.savepay.domain.payment.entity.pay.repository.PayRepository;
import savepay.savepay.global.aws.AwsS3Service;
import savepay.savepay.global.code.status.ErrorStatus;
import savepay.savepay.global.exception.GeneralException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PayService {

    private final PayRepository payRepository;

    private final AwsS3Service awsS3Service;

    @Transactional
    public PayResponseDto.PayRegistryResponseDto register(MultipartFile img, PayRequestDto.PayRegistryDto payRegistryDto) {
        String image = awsS3Service.uploadFile(img);

        Pay pay = PayConverter.toEntity(payRegistryDto, image);

        payRepository.save(pay);

        return PayConverter.toRegistryDto(pay);
    }

    public PayResponseDto.PayRegistryResponseDto findById(Long payId) {
        return PayConverter.toRegistryDto(payRepository.findById(payId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.PAYMENT_NOT_FOUND)));
    }

    public List<PayResponseDto.PayRegistryResponseDto> findAll() {
        return payRepository.findAll().stream()
                .map(PayConverter::toRegistryDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteById(Long payId) {
        Pay pay = payRepository.findById(payId).orElseThrow(() ->
                new GeneralException(ErrorStatus.PAYMENT_NOT_FOUND)
        );

        payRepository.delete(pay);
    }
}
