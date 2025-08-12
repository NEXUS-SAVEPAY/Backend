package savepay.savepay.global.security;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import savepay.savepay.global.ApiResponse;
import savepay.savepay.global.security.domain.service.TokenService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final TokenService tokenService;

    @GetMapping("/access-token")
    public ApiResponse<?> getAccessToken(HttpServletRequest request) {
        String refreshToken = request.getHeader("Refresh-token");

        String accessToken = tokenService.reissueAccessToken(refreshToken);

        return ApiResponse.onSuccess(accessToken);
    }
}
