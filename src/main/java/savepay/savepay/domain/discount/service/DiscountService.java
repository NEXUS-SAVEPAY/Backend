package savepay.savepay.domain.discount.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import savepay.savepay.domain.discount.converter.DiscountConverter;
import savepay.savepay.domain.discount.dto.DiscountResponseDto;
import savepay.savepay.domain.discount.entity.Discount;
import savepay.savepay.domain.discount.repository.DiscountRepository;
import savepay.savepay.domain.user.entity.User;
import savepay.savepay.domain.user.repository.UserRepository;
import savepay.savepay.domain.usertelecom.entity.UserTelecom;
import savepay.savepay.domain.usertelecom.repository.UserTelecomRepository;
import savepay.savepay.global.code.status.ErrorStatus;
import savepay.savepay.global.exception.GeneralException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiscountService {

    private final DiscountRepository discountRepository;
    private final UserRepository userRepository;
    private final UserTelecomRepository userTelecomRepository;

    public List<DiscountResponseDto.DiscountInfo> getTelecomDiscount(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        UserTelecom userTelecom = userTelecomRepository.findByUser(user)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_TELECOM_NOT_FOUND));

        List<Discount> discounts = discountRepository.findByTelecomAndGradeAndPeriodAfter(
                userTelecom.getTelecom(),
                userTelecom.getGrade(),
                LocalDateTime.now());

        return discounts
                .stream()
                .map(DiscountConverter::toDiscountInfoDto)
                .collect(Collectors.toList());
    }
}
