package savepay.savepay.domain.telecom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import savepay.savepay.domain.common.BaseEntity;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Telecom extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private TelecomName telecomName;

    private String image;

}
