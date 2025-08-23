package savepay.savepay.domain.discount.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import savepay.savepay.domain.brand.entity.Brand;
import savepay.savepay.domain.brand.repository.BrandRepository;
import savepay.savepay.domain.discount.converter.DiscountConverter;
import savepay.savepay.domain.discount.dto.DiscountRequestDto;
import savepay.savepay.domain.discount.dto.DiscountResponseDto;
import savepay.savepay.domain.discount.entity.Discount;
import savepay.savepay.domain.discount.repository.DiscountRepository;
import savepay.savepay.domain.interestbrand.entity.InterestBrand;
import savepay.savepay.domain.interestbrand.entity.InterestBrandCategory;
import savepay.savepay.domain.interestbrand.repository.InterestBrandRepository;
import savepay.savepay.domain.payment.entity.Payment;
import savepay.savepay.domain.user.entity.User;
import savepay.savepay.domain.userpayment.entity.UserPayment;
import savepay.savepay.domain.userpayment.repository.UserPaymentRepository;
import savepay.savepay.domain.usertelecom.entity.UserTelecom;
import savepay.savepay.domain.usertelecom.repository.UserTelecomRepository;
import savepay.savepay.global.code.status.ErrorStatus;
import savepay.savepay.global.exception.GeneralException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DiscountQueryService {

    private final DiscountRepository discountRepository;
    private final UserTelecomRepository userTelecomRepository;
    private final BrandRepository brandRepository;
    private final InterestBrandRepository interestBrandRepository;
    private final UserPaymentRepository userPaymentRepository;

    public List<DiscountResponseDto.DiscountInfo> getAllDiscounts(User user, DiscountRequestDto.toBrandNameDto request) {
        List<DiscountResponseDto.DiscountInfo> allDiscounts = new ArrayList<>();

        allDiscounts.addAll(getCardDiscount(user, request));
        allDiscounts.addAll(getPayDiscount(user, request));
        allDiscounts.addAll(getTelecomDiscount(user, request));

        return allDiscounts;
    }

    public List<DiscountResponseDto.DiscountInfo> getInterestBrandDiscount(User user) {
        List<InterestBrand> interestBrands = interestBrandRepository.findByUser(user)
                .stream()
                .filter(interestBrand -> interestBrand.getCategory().equals(InterestBrandCategory.INTEREST))
                .toList();

        if (interestBrands.isEmpty()) {
            throw new GeneralException(ErrorStatus.INTERESTED_BRAND_NOT_FOUND);
        }

        List<Brand> brands = interestBrands.stream()
                .map(InterestBrand::getBrand)
                .toList();

        List<Discount> discountInterestBrands =
                discountRepository.findByBrands(brands, LocalDateTime.now());

        return discountInterestBrands.stream()
                .map(DiscountConverter::toDiscountInfoDto)
                .collect(Collectors.toList());
    }

    public List<DiscountResponseDto.DiscountInfo> getUserTopDiscounts(User user) {
        List<DiscountResponseDto.DiscountInfo> topDiscounts = new ArrayList<>();

        List<DiscountResponseDto.DiscountInfo> cardDiscounts = getCardDiscountsOrEmpty(user);
        topDiscounts.addAll(cardDiscounts.stream().limit(2).collect(Collectors.toList()));

        List<DiscountResponseDto.DiscountInfo> payDiscounts = getPayDiscountsOrEmpty(user);
        topDiscounts.addAll(payDiscounts.stream().limit(2).collect(Collectors.toList()));

        List<DiscountResponseDto.DiscountInfo> telecomDiscounts = getTelecomDiscountsOrEmpty(user);
        topDiscounts.addAll(telecomDiscounts.stream().limit(2).collect(Collectors.toList()));

        return topDiscounts;
    }

    // 혜택추천은 6개이므로 각자 6개씩 가져와서 비교하여 리턴
    public List<DiscountResponseDto.DiscountInfo> getUserRecommendedDiscount(User user) {
        List<DiscountResponseDto.DiscountInfo> topDiscounts = new ArrayList<>();

        List<DiscountResponseDto.DiscountInfo> cardDiscounts = getCardDiscountsOrEmpty(user);
        topDiscounts.addAll(cardDiscounts.stream().limit(6).collect(Collectors.toList()));

        List<DiscountResponseDto.DiscountInfo> payDiscounts = getPayDiscountsOrEmpty(user);
        topDiscounts.addAll(payDiscounts.stream().limit(6).collect(Collectors.toList()));

        List<DiscountResponseDto.DiscountInfo> telecomDiscounts = getTelecomDiscountsOrEmpty(user);
        topDiscounts.addAll(telecomDiscounts.stream().limit(6).collect(Collectors.toList()));

        Comparator<DiscountResponseDto.DiscountInfo> byScore = Comparator
                .comparingInt(DiscountResponseDto.DiscountInfo::getDiscountPercent).reversed()
                .thenComparing((DiscountResponseDto.DiscountInfo d) -> Optional.ofNullable(d.getCreatedAt()).orElse(LocalDateTime.of(1900, 1, 1, 12, 0, 0)), Comparator.reverseOrder());

        return topDiscounts.stream()
                .sorted(byScore)
                .limit(6)
                .toList();
    }

    public List<DiscountResponseDto.DiscountInfo> getInterestOrPayment(User user) {
        try {
            return getInterestBrandDiscount(user);
        } catch (GeneralException e) {
            return getUserTopDiscounts(user);
        }
    }

    public List<DiscountResponseDto.DiscountInfo> getCardDiscounts(User user) {
        List<UserPayment> userCards = userPaymentRepository.findUserCardList(user);

        if (userCards.isEmpty()) {
            throw new GeneralException(ErrorStatus.USER_CARD_NOT_FOUND);
        }

        List<Payment> cards = userCards.stream()
                .map(UserPayment::getPayment)
                .collect(Collectors.toList());

        List<Discount> cardDiscountsByPayments = discountRepository.findCardDiscountsByPayments(cards, LocalDateTime.now());

        return cardDiscountsByPayments.stream()
                .map(DiscountConverter::toDiscountInfoDto)
                .collect(Collectors.toList());
    }

    public List<DiscountResponseDto.DiscountInfo> getTelecomDiscounts(User user) {
        UserTelecom userTelecom = userTelecomRepository.findByUser(user)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_TELECOM_NOT_FOUND));

        List<Discount> telecomDiscounts =
                discountRepository.findTelecomDiscounts(userTelecom.getTelecom(), userTelecom.getGrade(), LocalDateTime.now());

        return telecomDiscounts.stream()
                .map(DiscountConverter::toDiscountInfoDto)
                .collect(Collectors.toList());
    }

    public List<DiscountResponseDto.DiscountInfo> getPayDiscounts(User user) {
        List<UserPayment> userPays = userPaymentRepository.findUserPayList(user);

        if (userPays.isEmpty()) {
            throw new GeneralException(ErrorStatus.USER_PAY_NOT_FOUND);
        }

        List<Payment> pays = userPays.stream()
                .map(UserPayment::getPayment)
                .collect(Collectors.toList());

        List<Discount> payDiscountsByPayments = discountRepository.findPayDiscountsByPayments(pays, LocalDateTime.now());

        return payDiscountsByPayments.stream()
                .map(DiscountConverter::toDiscountInfoDto)
                .collect(Collectors.toList());
    }

    public DiscountResponseDto.DiscountInfo getDiscountInfo(Long discountId) {
        Discount discount = discountRepository.findById(discountId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.DISCOUNT_NOT_FOUND));

        return DiscountConverter.toDiscountInfoDto(discount);
    }

    private List<DiscountResponseDto.DiscountInfo> getCardDiscount(User user, DiscountRequestDto.toBrandNameDto request) {
        Brand brand = getBrand(request.getBrandName());

        List<UserPayment> userCards = userPaymentRepository.findUserCardList(user);

        if (userCards.isEmpty()) {
            throw new GeneralException(ErrorStatus.USER_CARD_NOT_FOUND);
        }

        List<Payment> cards = userCards.stream()
                .map(UserPayment::getPayment)
                .collect(Collectors.toList());

        List<Discount> discountCard = discountRepository.findCardDiscountByBrand(
                brand,
                cards,
                LocalDateTime.now()
        );

        return discountCard.stream()
                .map(DiscountConverter::toDiscountInfoDto)
                .collect(Collectors.toList());
    }

    private List<DiscountResponseDto.DiscountInfo> getPayDiscount(User user, DiscountRequestDto.toBrandNameDto request) {
        Brand brand = getBrand(request.getBrandName());

        List<UserPayment> userPays = userPaymentRepository.findUserPayList(user);

        if (userPays.isEmpty()) {
            throw new GeneralException(ErrorStatus.USER_PAY_NOT_FOUND);
        }

        List<Payment> pays = userPays.stream()
                .map(UserPayment::getPayment)
                .collect(Collectors.toList());

        List<Discount> discountPays = discountRepository.findPayDiscountByBrand(
                brand,
                pays,
                LocalDateTime.now()
        );

        return discountPays.stream()
                .map(DiscountConverter::toDiscountInfoDto)
                .collect(Collectors.toList());
    }

    private List<DiscountResponseDto.DiscountInfo> getTelecomDiscount(User user, DiscountRequestDto.toBrandNameDto request) {
        Brand brand = getBrand(request.getBrandName());

        UserTelecom userTelecom = userTelecomRepository.findByUser(user)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_TELECOM_NOT_FOUND));

        List<Discount> discounts = discountRepository.findTelecomDiscountByBrand(
                brand,
                userTelecom.getTelecom(),
                userTelecom.getGrade(),
                LocalDateTime.now()
        );

        return discounts
                .stream()
                .map(DiscountConverter::toDiscountInfoDto)
                .collect(Collectors.toList());
    }

    private Brand getBrand(String brandName) {
        return brandRepository.findByName(brandName)
                .orElseThrow(() -> new GeneralException(ErrorStatus.BRAND_NOT_FOUND));
    }

    private List<DiscountResponseDto.DiscountInfo> getTelecomDiscountsOrEmpty(User user) {
        try {
            return getTelecomDiscounts(user);
        } catch (GeneralException e) {
            return new ArrayList<>();

        }
    }

    private List<DiscountResponseDto.DiscountInfo> getPayDiscountsOrEmpty(User user) {
        try {
            return getPayDiscounts(user);
        } catch (GeneralException e) {
            return new ArrayList<>();
        }
    }

    private List<DiscountResponseDto.DiscountInfo> getCardDiscountsOrEmpty(User user) {
        try {
            return getCardDiscounts(user);
        } catch (GeneralException e) {
            return new ArrayList<>();
        }
    }
}
