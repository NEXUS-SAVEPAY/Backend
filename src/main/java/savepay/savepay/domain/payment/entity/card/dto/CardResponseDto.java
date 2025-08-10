package savepay.savepay.domain.payment.entity.card.dto;


public record CardResponseDto(
        Long cardId,
        String image,
        String company,
        String cardName
) {
}
