package savepay.savepay.domain.telecom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import savepay.savepay.domain.common.BaseEntity;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Telecom extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private TelecomName telecomName;
}
