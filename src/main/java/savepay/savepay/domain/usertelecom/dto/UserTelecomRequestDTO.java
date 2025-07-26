package savepay.savepay.domain.usertelecom.dto;

import lombok.Builder;

@Builder
public record UserTelecomRequestDTO(
        String telecomName,
        Boolean isMemberShip,
        String grade

) {
}
