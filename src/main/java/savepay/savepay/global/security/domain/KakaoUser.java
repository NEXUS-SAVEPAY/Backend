package savepay.savepay.global.security.domain;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

public class KakaoUser extends OAuth2ProviderUser {

    public KakaoUser(OAuth2User oAuth2User, ClientRegistration clientRegistration) {
        super((Map<String, Object>)oAuth2User.getAttributes().get("kakao_account"), oAuth2User, clientRegistration);
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getUsername() {
        return  (String) ((Map<String, String>)(getAttributes().get("profile"))).get("nickname");
    }

    @Override
    public String getName() {
        return getUsername();
    }
}
