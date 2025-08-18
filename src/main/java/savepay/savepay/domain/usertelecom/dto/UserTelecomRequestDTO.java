package savepay.savepay.domain.usertelecom.dto;

import savepay.savepay.domain.telecom.entity.TelecomName;
import savepay.savepay.domain.usertelecom.entity.TelecomGrade;

public record UserTelecomRequestDTO(
        TelecomName telecomName,
        Boolean isMembership,
        TelecomGrade grade

) {
}
