package savepay.savepay.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import savepay.savepay.domain.user.converter.UserConverter;
import savepay.savepay.domain.user.entity.User;
import savepay.savepay.domain.user.repository.UserRepository;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    /*
        이메일 중복 검증은 OAuth2UserService에서 이루어졌음
     */
    @Transactional
    public User register(String registrationId, String username, String email) {
        User user = UserConverter.toEntity(registrationId, username, email);

        userRepository.save(user);
        return user;
    }
}
