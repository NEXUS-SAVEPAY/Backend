package savepay.savepay.domain.usertelecom.dto;

import lombok.Builder;
import savepay.savepay.domain.telecom.entity.TelecomName;
import savepay.savepay.domain.usertelecom.entity.TelecomGrade;

@Builder
public record UserTelecomRequestDTO(
        TelecomName telecomName,
        Boolean isMemberShip,
        TelecomGrade grade

) {
}
