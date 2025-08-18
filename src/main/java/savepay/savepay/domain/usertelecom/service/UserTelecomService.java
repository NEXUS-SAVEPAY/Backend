package savepay.savepay.domain.usertelecom.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import savepay.savepay.domain.telecom.repository.TelecomRepository;
import savepay.savepay.domain.telecom.entity.Telecom;
import savepay.savepay.domain.user.entity.User;
import savepay.savepay.domain.usertelecom.converter.UserTelecomConverter;
import savepay.savepay.domain.usertelecom.dto.UserTelecomRequestDTO;
import savepay.savepay.domain.usertelecom.dto.UserTelecomResponseDTO;
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


    @Transactional
    public UserTelecomResponseDTO connectTelecom(UserTelecomRequestDTO requestDTO, User user) {
        Telecom telecom = telecomRepository.findByTelecomName(requestDTO.telecomName());

        UserTelecom userTelecom = UserTelecomConverter.toEntity(user, telecom, requestDTO);

        userTelecomRepository.save(userTelecom);

        return UserTelecomConverter.toDTO(userTelecom);

    }

    public UserTelecomResponseDTO findTelecom(User user) {
        UserTelecom userTelecom = userTelecomRepository.findByUser(user).orElseThrow(
                () -> new GeneralException(ErrorStatus.USER_TELECOM_NOT_FOUND)
        );

        return UserTelecomConverter.toDTO(userTelecom);
    }

    @Transactional
    public UserTelecomResponseDTO modifyTelecom(UserTelecomRequestDTO dto, User user) {
        UserTelecom userTelecom = userTelecomRepository.findByUser(user).orElseThrow(() ->
                new GeneralException(ErrorStatus.USER_TELECOM_NOT_FOUND));
        Telecom telecomName = telecomRepository.findByTelecomName(dto.telecomName());

        userTelecom.modifyUserTelecom(telecomName, dto.grade(), dto.isMembership());
        userTelecomRepository.save(userTelecom);

        return UserTelecomConverter.toDTO(userTelecom);
      }

    public boolean onboardingUser(User user) {
        return userTelecomRepository.findByUser(user).isPresent();
    }
}
