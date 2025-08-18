package savepay.savepay.domain.payment.entity.card.converter;


import savepay.savepay.domain.payment.entity.card.dto.CardRequestDto;
import savepay.savepay.domain.payment.entity.card.dto.CardResponseDto;
import savepay.savepay.domain.payment.entity.card.entity.Card;

public class CardConverter {

    public static CardResponseDto toDto(Card card) {
        return new CardResponseDto(card.getId(), card.getImage(),
                card.getCompany(), card.getCardName());
    }

    public static Card toEntity(CardRequestDto.CardRegisterDto dto,  String image) {
        return Card.createCard(image, dto.company(), dto.cardName());
    }
}
