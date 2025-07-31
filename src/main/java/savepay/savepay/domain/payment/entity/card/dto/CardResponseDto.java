package savepay.savepay.domain.payment.entity.card.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

public record CardResponseDto(
        Long cardId,
        String image,
        String company,
        String cardName
) {
}
