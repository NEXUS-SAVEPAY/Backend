package savepay.savepay.domain.telecom.entity;

import jakarta.persistence.*;
import lombok.Getter;
import savepay.savepay.domain.common.BaseEntity;
import savepay.savepay.domain.user.entity.User;

@Entity
@Getter
public class Telecom extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private TelecomName telecomName;
}
