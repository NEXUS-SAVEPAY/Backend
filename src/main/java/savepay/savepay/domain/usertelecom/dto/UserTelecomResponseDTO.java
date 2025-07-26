package savepay.savepay.domain.usertelecom.dto;

import lombok.Builder;

@Builder
public record UserTelecomResponseDTO(
        Long userId,
        String telecomName,
        String grade,
        boolean isMemberShip
) {
}
