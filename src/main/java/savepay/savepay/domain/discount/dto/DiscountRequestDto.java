package savepay.savepay.domain.discount.dto;

import lombok.Getter;
import savepay.savepay.domain.discount.entity.DiscountType;
import savepay.savepay.domain.telecom.entity.TelecomName;
import savepay.savepay.domain.usertelecom.entity.TelecomGrade;

import java.time.LocalDateTime;

public class DiscountRequestDto {

    @Getter
    public static class toDiscountInfo {
        int discountPercent;
        LocalDateTime discountDate;
        String condition;
        String infoLink;
        String details;
        String pointInfo;
        String brandName;
        Long paymentId;
        TelecomName telecomName;
        TelecomGrade telecomGrade;
        DiscountType discountType;
    }

    @Getter
    public static class toBrandNameDto {
        String brandName;
    }
}
