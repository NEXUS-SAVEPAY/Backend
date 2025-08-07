package savepay.savepay.domain.usertelecom.entity;

import org.junit.jupiter.api.Test;
import savepay.savepay.domain.telecom.entity.Telecom;
import savepay.savepay.domain.telecom.entity.TelecomName;
import savepay.savepay.domain.user.entity.Social;
import savepay.savepay.domain.user.entity.User;

import static org.assertj.core.api.Assertions.assertThat;


class UserTelecomTest {
    @Test
    void createUserTelecom() {
        //given
        User user =  User.createUser("lee", "12341234@email.com", Social.NAVER);
        Telecom telecom = new Telecom(TelecomName.KT);
        //when
        UserTelecom userTelecom = UserTelecom.createUserTelecom(user, telecom, TelecomGrade.VIP, true);

        //then
        assertThat(userTelecom.getTelecom().getTelecomName()).isEqualTo(TelecomName.KT);
        assertThat(userTelecom.getUser().getUsername()).isEqualTo("lee");
    }
}