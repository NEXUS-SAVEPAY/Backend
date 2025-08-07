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

import java.io.IOException;
import java.util.Map;

/*
    OAuth2 인증이 성공하면, 이를 바탕으로 JWT Token을 생성합니다.
 */
@Component
@RequiredArgsConstructor
public class CustomSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        ProviderUser principal = (ProviderUser) authentication.getPrincipal();

        String email = principal.getEmail();
        String accessToken = jwtTokenProvider.generateAccessToken(email);
        String refreshToken = jwtTokenProvider.generateRefreshToken(email);

        response.setHeader("Authorization", "Bearer " + accessToken);
        response.setHeader("Refresh-token", "Bearer " + refreshToken);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Map<String, String> body = Map.of("accessToken", accessToken
        , "refreshToken", refreshToken);

        String json = objectMapper.writeValueAsString(body);
        response.getWriter().write(json);
    }

//    private CustomOAuth2AuthenticationFilter customOAuth2AuthenticationFilter() {
//        CustomOAuth2AuthenticationFilter customOAuth2AuthenticationFilter = new CustomOAuth2AuthenticationFilter(authorizedClientManager, authorizedClientRepository
//                , jwtTokenProvider, memberRepository);
//        customOAuth2AuthenticationFilter.setAuthenticationSuccessHandler(
//                ((request, response, authentication) ->
//                {
//                    String authorization = (String)request.getAttribute("Authorization");
//                    String refresh = (String)request.getAttribute("RefreshToken");
//                    response.sendRedirect("/home?access="+authorization+"&refresh="+refresh);
//                }
//                )
//
//        );
//        return customOAuth2AuthenticationFilter;
//
//    }
}
