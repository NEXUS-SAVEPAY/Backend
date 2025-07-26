package savepay.savepay.domain.usertelecom.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import savepay.savepay.domain.telecom.Telecom.TelecomRepository;
import savepay.savepay.domain.telecom.converter.TelecomConverter;
import savepay.savepay.domain.telecom.entity.Telecom;
import savepay.savepay.domain.telecom.entity.TelecomName;
import savepay.savepay.domain.user.entity.User;
import savepay.savepay.domain.usertelecom.converter.UserTelecomConverter;
import savepay.savepay.domain.usertelecom.dto.UserTelecomRequestDTO;
import savepay.savepay.domain.usertelecom.entity.UserTelecom;
import savepay.savepay.domain.usertelecom.repository.UserTelecomRepository;
import savepay.savepay.global.code.status.ErrorStatus;
import savepay.savepay.global.exception.GeneralException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserTelecomService {

    private final UserTelecomRepository userTelecomRepository;

    private final TelecomRepository telecomRepository;


    public void connectTelecom(UserTelecomRequestDTO requestDTO, User user) {
        TelecomName telecomName = TelecomConverter.nameToEnum(requestDTO.telecomName());

        Telecom telecom = telecomRepository.findByTelecomName(telecomName);

        UserTelecom userTelecom = UserTelecomConverter.toEntity(user, telecom, requestDTO);

        userTelecomRepository.save(userTelecom);

    }

    public void findTelecom(User user) {
        UserTelecom userTelecom = userTelecomRepository.findByUser(user).orElseThrow(
                () -> new GeneralException(ErrorStatus.USER_TELECOM_NOT_FOUND)
        );
    }
}
