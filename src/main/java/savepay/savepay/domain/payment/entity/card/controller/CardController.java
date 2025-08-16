package savepay.savepay.domain.payment.entity.card.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import savepay.savepay.domain.payment.entity.card.dto.CardRequestDto;
import savepay.savepay.domain.payment.entity.card.dto.CardResponseDto;
import savepay.savepay.domain.payment.entity.card.service.CardService;
import savepay.savepay.domain.usercard.service.UserCardService;
import savepay.savepay.domain.user.entity.User;
import savepay.savepay.global.ApiResponse;
import savepay.savepay.global.security.resolver.UserInjection;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    private final UserCardService userCardService;

    @PostMapping("/admin")
    public ApiResponse<CardResponseDto> postCard(@RequestBody CardRequestDto.CardRegisterDto cardRegisterDto) {
        return ApiResponse.onSuccess(cardService.registerCard(cardRegisterDto));
    }

    @DeleteMapping("/admin")
    public ApiResponse<String> deleteCard(@RequestParam Long cardId) {
        cardService.deleteCard(cardId);

        return ApiResponse.onSuccess("Card is deleted!!");
    }

    @GetMapping("/list")
    public ApiResponse<List<CardResponseDto>> findCardList(@UserInjection User user) {
        return ApiResponse.onSuccess(userCardService.findUserCardList(user));
    }

    @GetMapping("/search")
    public ApiResponse<CardResponseDto> searchCard(CardRequestDto.CardSearchDto cardSearchDto) {
        return ApiResponse.onSuccess(cardService.searchCard(cardSearchDto));
    }

    @PostMapping("/user")
    public ApiResponse<String> postCard(@RequestParam(name = "cardId")Long cardId, @UserInjection User user) {
        userCardService.registerUserCard(cardId, user);
        return ApiResponse.onSuccess("User's card is successfully registered");
    }

    @DeleteMapping("/user")
    public ApiResponse<String> deleteCard(@RequestParam(name = "cardId")Long cardId, @UserInjection User user) {
        userCardService.deleteUserCard(cardId, user);
        return ApiResponse.onSuccess("User's card is successfully deleted");
    }

}
