package savepay.savepay.domain.usertelecom.dto;

import lombok.Builder;

@Builder
public record UserTelecomResponseDTO(
        Long userId,
        String TelecomName,
        String grade,
        boolean isMemberShip
) {
}
