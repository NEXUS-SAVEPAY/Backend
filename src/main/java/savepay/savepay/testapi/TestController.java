package savepay.savepay.testapi;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import savepay.savepay.domain.user.entity.User;
import savepay.savepay.domain.user.repository.UserRepository;
import savepay.savepay.domain.user.service.UserService;
import savepay.savepay.global.code.status.ErrorStatus;
import savepay.savepay.global.exception.GeneralException;
import savepay.savepay.global.security.domain.token.service.TokenService;

/**
 * 테스트 전용으로 만들어진 API입니다.
 * 테스트 계정 생성, jwt반환 등의 역할을 수행합니다.
 */
@RestController
@RequiredArgsConstructor
public class TestController {

    private final UserService userService;

    private final UserRepository userRepository;

    private final TokenService tokenService;

    @PostMapping("/test/user")
    public String createUser(@RequestParam String email, @RequestParam String username) {
        userService.register("NAVER", username, email);
        return "okay";
    }

    @GetMapping("/test/token")
    public String generateToken(@RequestParam String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new GeneralException(ErrorStatus.USER_NOT_FOUND));

        return tokenService.generateAccessToken(email);
    }

}
