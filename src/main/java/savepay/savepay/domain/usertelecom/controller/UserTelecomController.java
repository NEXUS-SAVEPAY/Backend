package savepay.savepay.domain.usertelecom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import savepay.savepay.domain.user.entity.User;
import savepay.savepay.domain.usertelecom.dto.UserTelecomRequestDTO;
import savepay.savepay.domain.usertelecom.dto.UserTelecomResponseDTO;
import savepay.savepay.domain.usertelecom.service.UserTelecomService;
import savepay.savepay.global.ApiResponse;

/*
    추후 SpringSecurity 연동으로 User를 자동으로 주입받을 수 있다고 가정하고 작성했습니다.
    자동 주입 관련해서 빠르게 개발해보도록 하겠습니다.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user-telecoms")
public class UserTelecomController {

    private final UserTelecomService userTelecomService;

    @GetMapping("/")
    ApiResponse<UserTelecomResponseDTO> findUserTelecom(User user) {
        return ApiResponse.onSuccess(userTelecomService.findTelecom(user));
    }

    @PostMapping("/")
    ApiResponse<UserTelecomResponseDTO> connectUserTelecom(User user, UserTelecomRequestDTO dto) {
        return ApiResponse.onSuccess(userTelecomService.connectTelecom(dto, user));
    }
}
