package savepay.savepay.global.security.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import savepay.savepay.domain.user.entity.User;
import savepay.savepay.domain.user.repository.UserRepository;
import savepay.savepay.global.code.status.ErrorStatus;
import savepay.savepay.global.exception.GeneralException;
import savepay.savepay.global.security.JwtTokenProvider;
import savepay.savepay.global.security.domain.RefreshToken;
import savepay.savepay.global.security.domain.repository.RefreshTokenRepository;
import savepay.savepay.global.security.service.CustomUserDetailsService;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TokenService {

    private final UserRepository userRepository;

    private final RefreshTokenRepository refreshTokenRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final CustomUserDetailsService customUserDetailsService;

    private static final String BEARER_PREFIX = "Bearer ";

    public boolean validateRefreshToken(String refreshToken) {
        if (!jwtTokenProvider.isRefresh(refreshToken) || !jwtTokenProvider.validateToken(refreshToken)) {
            throw new GeneralException(ErrorStatus.INVALID_TOKEN);
        }

        String email = jwtTokenProvider.getUsernameFromToken(refreshToken);

        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new GeneralException(ErrorStatus.USER_NOT_FOUND)
        );
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByUser(user);

        // Refresh hash적용 가능성 생각해보기
        return optionalRefreshToken.map(token ->
                token.getRefreshToken().equals(refreshToken)).orElse(false);
    }

    public boolean validateAccessToken(String accessToken) {
        return jwtTokenProvider.validateToken(accessToken) && jwtTokenProvider.isAccess(accessToken);
    }

    public String generateAccessToken(String email) {
        return jwtTokenProvider.generateAccessToken(email);
    }

    public UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
        String accessToken = resolveBearerToken(token).orElseThrow(() ->
                new GeneralException(ErrorStatus.INVALID_TOKEN));
        if (!validateAccessToken(accessToken)) {
            throw new GeneralException(ErrorStatus.INVALID_TOKEN);
        }
        String email = jwtTokenProvider.getUsernameFromToken(accessToken);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }


    public String reissueAccessToken(String token) {
        String refreshToken = resolveBearerToken(token).orElseThrow(() ->
                new GeneralException(ErrorStatus.INVALID_TOKEN));
        if (!validateRefreshToken(refreshToken)) {
            throw new GeneralException(ErrorStatus.INVALID_TOKEN);
        }
        return jwtTokenProvider.generateAccessToken(jwtTokenProvider.getUsernameFromToken(refreshToken));
    }

    public String generateRefreshToken(String email) {
        String refreshToken = jwtTokenProvider.generateRefreshToken(email);
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new GeneralException(ErrorStatus.USER_NOT_FOUND));

        refreshTokenRepository.findByUser(user).ifPresent(refreshTokenRepository::delete);

        RefreshToken refreshTokenObject = RefreshToken.createRefreshToken(user, refreshToken);
        refreshTokenRepository.save(refreshTokenObject);

        return refreshToken;
    }

    public static Optional<String> resolveBearerToken(String BearerToken) {
        if (StringUtils.hasText(BearerToken) && BearerToken.regionMatches(true, 0, BEARER_PREFIX, 0, BEARER_PREFIX.length())) {
            String token = BearerToken.substring(BEARER_PREFIX.length()).trim();
            return StringUtils.hasText(token) ? Optional.of(token) : Optional.empty();
        }
        return Optional.empty();
    }

}
