package savepay.savepay.domain.usertelecom.dto;

import lombok.Builder;
import savepay.savepay.domain.telecom.entity.TelecomName;
import savepay.savepay.domain.usertelecom.entity.TelecomGrade;

@Builder
public record UserTelecomResponseDTO(
        Long userId,
        TelecomName telecomName,
        TelecomGrade grade,
        boolean isMemberShip
) {
}
