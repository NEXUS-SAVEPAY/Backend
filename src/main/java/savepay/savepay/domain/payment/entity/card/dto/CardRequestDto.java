package savepay.savepay.domain.payment.entity.card.dto;

public class CardRequestDto {
    /*
            카드 검색용
         */
    public record CardSearchDto(
            String company,
            String cardName
    ) {}

    public record CardRegisterDto(
            String image,
            String cardName,
            String company
    ) {}
}
