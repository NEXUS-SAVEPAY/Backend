package savepay.savepay.domain.payment.entity.card.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import savepay.savepay.domain.payment.entity.card.dto.CardRequestDto;
import savepay.savepay.domain.payment.entity.card.dto.CardResponseDto;
import savepay.savepay.domain.payment.entity.card.service.CardService;
import savepay.savepay.global.ApiResponse;

import java.util.List;

@RequestMapping("/api/admin/cards")
@RestController
@RequiredArgsConstructor
public class CardAdminController {

    private final CardService cardService;

    @PostMapping(name = "/", consumes = "multipart/form-data")
    public ApiResponse<CardResponseDto> postCard(@RequestPart(name = "ImageFile", required = false) MultipartFile img,
                                                 @RequestPart CardRequestDto.CardRegisterDto cardRegisterDto) {
        return ApiResponse.onSuccess(cardService.registerCard(img, cardRegisterDto));
    }

    @DeleteMapping("/")
    public ApiResponse<String> deleteCard(@RequestParam Long cardId) {
        cardService.deleteCard(cardId);

        return ApiResponse.onSuccess("Card is deleted!!");
    }

    @GetMapping("/")
    public ApiResponse<CardResponseDto> findCard(@RequestParam Long cardId) {
        return ApiResponse.onSuccess(cardService.findById(cardId));
    }

    @GetMapping("/list")
    public ApiResponse<List<CardResponseDto>> findAll() {
        return ApiResponse.onSuccess(cardService.findAll());
    }
}
