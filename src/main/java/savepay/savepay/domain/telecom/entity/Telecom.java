package savepay.savepay.domain.telecom.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import savepay.savepay.domain.common.BaseEntity;
import savepay.savepay.domain.user.entity.User;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Telecom extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private TelecomName telecomName;
}
