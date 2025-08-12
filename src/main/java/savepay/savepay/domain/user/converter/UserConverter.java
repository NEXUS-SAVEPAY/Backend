package savepay.savepay.domain.user.converter;

import savepay.savepay.domain.user.entity.Social;
import savepay.savepay.domain.user.entity.User;

public class UserConverter {

    public static User toEntity(String registrationId, String username, String email) {
        Social social = Social.valueOf(registrationId.toUpperCase());

        return User.createUser(username, email, social);
    }

}
