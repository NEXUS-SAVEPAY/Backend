package savepay.savepay.global.security.domain.token.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import savepay.savepay.global.ApiResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TokenResponseWriter {

    private final ObjectMapper objectMapper;

//    public void write(HttpServletResponse response, String access, String refresh) throws IOException, IOException {
//        response.setStatus(HttpServletResponse.SC_OK);
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        var body = Map.of("accessToken", access, "refreshToken", refresh);
//        response.getWriter().write(objectMapper.writeValueAsString(ApiResponse.onSuccess(body)));
//    }

    public void write(HttpServletResponse response, String access, String refresh) throws IOException, IOException {
        response.setStatus(HttpServletResponse.SC_FOUND);
        String redirectUrl = String.format(
                "http://localhost:5173/auth/callback?accessToken=%s&refreshToken=%s",
                URLEncoder.encode(access, StandardCharsets.UTF_8),
                URLEncoder.encode(refresh, StandardCharsets.UTF_8));

        response.setHeader("Location", redirectUrl);
    }

    public void writeAccessOnly(HttpServletResponse response, String access) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        var body = Map.of("accessToken", access);
        response.getWriter().write(objectMapper.writeValueAsString(ApiResponse.onSuccess(body)));
    }
}
