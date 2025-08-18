package savepay.savepay.domain.discount.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import savepay.savepay.domain.brand.entity.Brand;
import savepay.savepay.domain.brand.repository.BrandRepository;
import savepay.savepay.domain.discount.dto.DiscountRequestDto;
import savepay.savepay.domain.discount.entity.Discount;
import savepay.savepay.domain.discount.repository.DiscountRepository;
import savepay.savepay.domain.payment.entity.Payment;
import savepay.savepay.domain.payment.entity.card.repository.CardRepository;
import savepay.savepay.domain.payment.entity.pay.repository.PayRepository;
import savepay.savepay.domain.telecom.repository.TelecomRepository;
import savepay.savepay.domain.telecom.entity.Telecom;
import savepay.savepay.global.code.status.ErrorStatus;
import savepay.savepay.global.exception.GeneralException;

@Service
@RequiredArgsConstructor
@Transactional
public class DiscountService {

    private final DiscountRepository discountRepository;
    private final BrandRepository brandRepository;
    private final TelecomRepository telecomRepository;
    private final PayRepository payRepository;
    private final CardRepository cardRepository;

    public void createDiscount(DiscountRequestDto.toDiscountInfo request) {
        Brand brand = getBrand(request.getBrandName());
        Telecom telecom = telecomRepository.findByTelecomName(request.getTelecomName());

        Discount discount = Discount.builder()
                .brand(brand)
                .discountPercent(request.getDiscountPercent())
                .condition(request.getCondition())
                .details(request.getDetails())
                .pointInfo(request.getPointInfo())
                .infoLink(request.getInfoLink())
                .period(request.getDiscountDate())
                .payment(request.getPaymentId() != null ? findPaymentById(request.getPaymentId()) : null)
                .type(request.getDiscountType())
                .grade(request.getTelecomGrade())
                .telecom(telecom)
                .build();

        discountRepository.save(discount);
    }

    private Payment findPaymentById(Long paymentId) {
        Payment payment = payRepository.findById(paymentId).orElse(null);
        if (payment != null) {
            return payment;
        }
        return cardRepository.findById(paymentId).orElse(null);
    }

    private Brand getBrand(String brandName) {
        return brandRepository.findByName(brandName)
                .orElseThrow(() -> new GeneralException(ErrorStatus.BRAND_NOT_FOUND));
    }
}
