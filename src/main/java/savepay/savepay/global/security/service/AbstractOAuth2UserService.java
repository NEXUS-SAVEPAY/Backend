package savepay.savepay.global.security.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import savepay.savepay.domain.user.entity.User;
import savepay.savepay.domain.user.repository.UserRepository;
import savepay.savepay.domain.user.service.UserService;
import savepay.savepay.global.security.domain.oauth2user.entity.KakaoUser;
import savepay.savepay.global.security.domain.oauth2user.entity.NaverUser;
import savepay.savepay.global.security.domain.oauth2user.entity.ProviderUser;

import java.util.Optional;

@Service
@Getter
public abstract class AbstractOAuth2UserService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    public void register(ProviderUser providerUser, OAuth2UserRequest userRequest){
        Optional<User> userOptional = userRepository.findByEmail(providerUser.getEmail());


        if(userOptional.isEmpty()){
            ClientRegistration clientRegistration = userRequest.getClientRegistration();
            userService.register(clientRegistration.getRegistrationId(),providerUser.getUsername(), providerUser.getEmail());
        }

    }

    public ProviderUser providerUser(ClientRegistration clientRegistration, OAuth2User oAuth2User){

        String registrationId = clientRegistration.getRegistrationId();

        if(registrationId.equals("naver")){
            return new NaverUser(oAuth2User,clientRegistration);
        }
        else if(registrationId.equals("kakao")) {
            return new KakaoUser(oAuth2User, clientRegistration);
        }
        return null;
    }
}
