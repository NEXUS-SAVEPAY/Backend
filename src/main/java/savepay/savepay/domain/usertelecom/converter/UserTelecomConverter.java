package savepay.savepay.domain.usertelecom.converter;

import org.springframework.stereotype.Component;
import savepay.savepay.domain.telecom.converter.TelecomConverter;
import savepay.savepay.domain.telecom.entity.Telecom;
import savepay.savepay.domain.telecom.entity.TelecomName;
import savepay.savepay.domain.user.entity.User;
import savepay.savepay.domain.usertelecom.dto.UserTelecomRequestDTO;
import savepay.savepay.domain.usertelecom.dto.UserTelecomResponseDTO;
import savepay.savepay.domain.usertelecom.entity.TelecomGrade;
import savepay.savepay.domain.usertelecom.entity.UserTelecom;

public class UserTelecomConverter {

    public static UserTelecomResponseDTO toDTO(UserTelecom userTelecom) {
        return UserTelecomResponseDTO.builder()
                .userId(userTelecom.getUser().getId())
                .telecomName(userTelecom.getTelecom().getTelecomName().name())
                .grade(userTelecom.getGrade().name())
                .isMemberShip(userTelecom.getIsMemberShip())
                .build();
    }

    public static UserTelecom toEntity(User user, Telecom telecom, UserTelecomRequestDTO requestDTO) {
        TelecomName telecomName = TelecomConverter.nameToEnum(requestDTO.telecomName());

        return UserTelecom.createUserTelecom(user, telecom, TelecomGrade.valueOf(requestDTO.grade()),
                requestDTO.isMemberShip());
    }
}
