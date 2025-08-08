package savepay.savepay.global.security.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import savepay.savepay.domain.user.entity.User;
import savepay.savepay.domain.user.repository.UserRepository;
import savepay.savepay.global.code.status.ErrorStatus;
import savepay.savepay.global.exception.GeneralException;
import savepay.savepay.global.security.domain.RefreshToken;
import savepay.savepay.global.security.domain.repository.RefreshTokenRepository;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RefreshTokenService {

    private final UserRepository userRepository;

    private final RefreshTokenRepository refreshTokenRepository;

    public boolean validateRefreshToken(String email, String refreshToken) {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new GeneralException(ErrorStatus.USER_NOT_FOUND)
        );
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByUser(user);

        return optionalRefreshToken.map(token ->
                token.getRefreshToken().equals(refreshToken)).orElse(false);
    }

}
