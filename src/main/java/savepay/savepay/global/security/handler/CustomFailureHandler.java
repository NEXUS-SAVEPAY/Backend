package savepay.savepay.global.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import savepay.savepay.global.ApiResponse;
import savepay.savepay.global.code.status.ErrorStatus;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        // 응답 상태 설정
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        log.info(exception.getMessage());

        // 에러 메시지 구성
        ApiResponse<Object> apiResponse = ApiResponse.onFailure(ErrorStatus.OAUTH2_LOGIN_FAIL.getCode(),
                ErrorStatus.OAUTH2_LOGIN_FAIL.getMessage(), null);


        // JSON으로 응답
        String json = objectMapper.writeValueAsString(apiResponse);
        response.getWriter().write(json);
    }
}
