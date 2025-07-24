package savepay.savepay.domain.telecom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import savepay.savepay.domain.common.BaseEntity;
import savepay.savepay.domain.user.entity.User;

@Entity
public class Telecom extends BaseEntity {

    private String grade;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
