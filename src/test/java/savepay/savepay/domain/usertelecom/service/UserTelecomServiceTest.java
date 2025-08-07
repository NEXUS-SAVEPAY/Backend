package savepay.savepay.domain.usertelecom.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import savepay.savepay.domain.telecom.Telecom.TelecomRepository;
import savepay.savepay.domain.telecom.entity.Telecom;
import savepay.savepay.domain.telecom.entity.TelecomName;
import savepay.savepay.domain.user.entity.Social;
import savepay.savepay.domain.user.entity.User;
import savepay.savepay.domain.user.repository.UserRepository;
import savepay.savepay.domain.usertelecom.dto.UserTelecomRequestDTO;
import savepay.savepay.domain.usertelecom.dto.UserTelecomResponseDTO;
import savepay.savepay.domain.usertelecom.entity.TelecomGrade;
import savepay.savepay.domain.usertelecom.entity.UserTelecom;
import savepay.savepay.domain.usertelecom.repository.UserTelecomRepository;
import savepay.savepay.global.exception.GeneralException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class UserTelecomServiceTest {

    @Autowired
    private UserTelecomService userTelecomService;

    @Autowired
    private UserTelecomRepository userTelecomRepository;

    @Autowired
    private TelecomRepository telecomRepository;

    @Autowired
    private UserRepository userRepository;

    User user;

    @BeforeEach
    void before() {
        user = User.createUser("test@example.com", "테스터", Social.KAKAO);
        userRepository.save(user);
    }

    @Test
    void connectTelecom_정상동작() {
        // given
        Telecom telecom = new Telecom(TelecomName.SKT);
        telecomRepository.save(telecom);

        UserTelecomRequestDTO dto = new UserTelecomRequestDTO("SKT", true, TelecomGrade.VIP.name());

        // when
        UserTelecomResponseDTO responseDTO = userTelecomService.connectTelecom(dto, user);

        // then
        UserTelecom saved = userTelecomRepository.findByUser(user).orElseThrow();
        assertThat(saved.getTelecom().getTelecomName()).isEqualTo(TelecomName.SKT);
        assertThat(saved.getGrade()).isEqualTo(TelecomGrade.VIP);
        assertThat(saved.getIsMemberShip()).isTrue();
    }


    @Test
    void findTelecom_없으면_예외_발생() {
        // given
        // when & then
        assertThrows(GeneralException.class, () -> {
            userTelecomService.findTelecom(user);
        });
    }

    @Test
    void findTelecom_정상조회() {
        // given
        Telecom telecom = new Telecom(TelecomName.KT);
        telecomRepository.save(telecom);

        UserTelecom userTelecom = UserTelecom.createUserTelecom(user, telecom, TelecomGrade.GOLD, true);
        userTelecomRepository.save(userTelecom);

        // when
        UserTelecomResponseDTO dto = userTelecomService.findTelecom(user);

        // then
        assertThat(dto.telecomName()).isEqualTo("KT");
        assertThat(dto.grade()).isEqualTo("GOLD");
    }


}