package savepay.savepay.domain.payment.entity.card.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import savepay.savepay.domain.payment.entity.card.converter.CardConverter;
import savepay.savepay.domain.payment.entity.card.dto.CardRequestDto;
import savepay.savepay.domain.payment.entity.card.dto.CardResponseDto;
import savepay.savepay.domain.payment.entity.card.entity.Card;
import savepay.savepay.domain.payment.entity.card.repository.CardRepository;
import savepay.savepay.global.code.status.ErrorStatus;
import savepay.savepay.global.exception.GeneralException;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    public CardResponseDto searchCard(CardRequestDto.CardSearchDto cardSearchDto) {
        Card card = cardRepository.searchCard(cardSearchDto.company(), cardSearchDto.cardName())
                .orElseThrow(
                        () -> new GeneralException(ErrorStatus.CARD_NOT_FOUND));

        return CardConverter.toDto(card);
    }

    public CardResponseDto registerCard(CardRequestDto.CardRegisterDto cardRegisterDto) {
        Card card = CardConverter.toEntity(cardRegisterDto);
        cardRepository.save(card);

        return CardConverter.toDto(card);
    }

    public void deleteCard(Long cardId) {
        Card card = cardRepository.findById(cardId).orElseThrow(
                () -> new GeneralException(ErrorStatus.CARD_NOT_FOUND));

        cardRepository.delete(card);
    }
}
