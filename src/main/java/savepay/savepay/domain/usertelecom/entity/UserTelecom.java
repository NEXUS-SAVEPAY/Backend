package savepay.savepay.domain.usertelecom.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import savepay.savepay.domain.common.BaseEntity;
import savepay.savepay.domain.telecom.entity.Telecom;
import savepay.savepay.domain.user.entity.User;

@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserTelecom extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "telecom_id")
    private Telecom telecom;


    private Boolean isMemberShip;

    @Enumerated(EnumType.STRING)
    private TelecomGrade grade;

    public static UserTelecom createUserTelecom(User user, Telecom telecom,
                                                TelecomGrade telecomGrade, Boolean isMembership) {
        return new UserTelecom(user, telecom, isMembership, telecomGrade);
    }

    public UserTelecom modifyUserTelecom(Telecom telecom, TelecomGrade telecomGrade,
                                                Boolean isMemberShip) {
        this.telecom = telecom;
        this.grade = telecomGrade;
        this.isMemberShip = isMemberShip;

        return this;
    }
}
