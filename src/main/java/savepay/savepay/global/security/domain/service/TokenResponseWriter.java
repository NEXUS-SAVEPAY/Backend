package savepay.savepay.global.security.domain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import savepay.savepay.global.ApiResponse;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TokenResponseWriter {

    private final ObjectMapper objectMapper;

    public void write(HttpServletResponse response, String access, String refresh) throws IOException, IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        var body = Map.of("accessToken", access, "refreshToken", refresh);
        response.getWriter().write(objectMapper.writeValueAsString(ApiResponse.onSuccess(body)));
    }

    public void writeAccessOnly(HttpServletResponse response, String access) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        var body = Map.of("accessToken", access);
        response.getWriter().write(objectMapper.writeValueAsString(ApiResponse.onSuccess(body)));
    }
}
