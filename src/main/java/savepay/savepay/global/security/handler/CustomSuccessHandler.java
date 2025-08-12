package savepay.savepay.global.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import savepay.savepay.global.security.domain.ProviderUser;
import savepay.savepay.global.security.domain.service.TokenResponseWriter;
import savepay.savepay.global.security.domain.service.TokenService;

import java.io.IOException;

/*
    OAuth2 인증이 성공하면, 이를 바탕으로 JWT Token을 생성합니다.
 */
@Component
@RequiredArgsConstructor
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    private final TokenService tokenService;

    private final TokenResponseWriter tokenResponseWriter;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        ProviderUser principal = (ProviderUser) authentication.getPrincipal();

        String email = principal.getEmail();
        String accessToken = tokenService.generateAccessToken(email);
        String refreshToken = tokenService.generateRefreshToken(email);

        tokenResponseWriter.write(response, accessToken, refreshToken);
    }
}
