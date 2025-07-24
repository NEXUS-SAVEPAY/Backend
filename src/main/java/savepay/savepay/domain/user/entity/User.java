package savepay.savepay.domain.user.entity;

import jakarta.persistence.Entity;
import savepay.savepay.domain.common.BaseEntity;

@Entity
public class User extends BaseEntity {

    private String username;

    private String email;

    private Social socialType;

}
