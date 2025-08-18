package savepay.savepay.domain.usertelecom.converter;

import savepay.savepay.domain.telecom.entity.Telecom;
import savepay.savepay.domain.user.entity.User;
import savepay.savepay.domain.usertelecom.dto.UserTelecomRequestDTO;
import savepay.savepay.domain.usertelecom.dto.UserTelecomResponseDTO;
import savepay.savepay.domain.usertelecom.entity.UserTelecom;

public class UserTelecomConverter {

    public static UserTelecomResponseDTO toDTO(UserTelecom userTelecom) {
        return UserTelecomResponseDTO.builder()
                .userId(userTelecom.getUser().getId())
                .telecomName(userTelecom.getTelecom().getTelecomName())
                .grade(userTelecom.getGrade())
                .isMembership(userTelecom.getIsMemberShip())
                .build();
    }

    public static UserTelecom toEntity(User user, Telecom telecom, UserTelecomRequestDTO requestDTO) {
        return UserTelecom.createUserTelecom(user, telecom, requestDTO.grade(),
                requestDTO.isMembership());
    }
}
