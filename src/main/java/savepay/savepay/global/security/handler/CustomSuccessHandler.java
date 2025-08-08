package savepay.savepay.global.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import savepay.savepay.global.security.JwtTokenProvider;
import savepay.savepay.global.security.domain.ProviderUser;
import savepay.savepay.global.security.domain.service.TokenResponseWriter;

import java.io.IOException;

/*
    OAuth2 인증이 성공하면, 이를 바탕으로 JWT Token을 생성합니다.
 */
@Component
@RequiredArgsConstructor
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;

    private final TokenResponseWriter tokenResponseWriter;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        ProviderUser principal = (ProviderUser) authentication.getPrincipal();

        String email = principal.getEmail();
        String accessToken = jwtTokenProvider.generateAccessToken(email);
        String refreshToken = jwtTokenProvider.generateRefreshToken(email);

        tokenResponseWriter.write(response, accessToken, refreshToken);
    }
}
