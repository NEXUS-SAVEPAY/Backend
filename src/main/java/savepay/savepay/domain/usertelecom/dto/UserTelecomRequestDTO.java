package savepay.savepay.domain.usertelecom.dto;

import lombok.Builder;
import savepay.savepay.domain.telecom.entity.TelecomName;

@Builder
public record UserTelecomRequestDTO(
        TelecomName telecomName,
        Boolean isMemberShip,
        String grade

) {
}
