package savepay.savepay.domain.payment.entity.card.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import savepay.savepay.domain.payment.entity.card.converter.CardConverter;
import savepay.savepay.domain.payment.entity.card.dto.CardRequestDto;
import savepay.savepay.domain.payment.entity.card.dto.CardResponseDto;
import savepay.savepay.domain.payment.entity.card.entity.Card;
import savepay.savepay.domain.payment.entity.card.repository.CardRepository;
import savepay.savepay.global.aws.AwsS3Service;
import savepay.savepay.global.code.status.ErrorStatus;
import savepay.savepay.global.exception.GeneralException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 카드 자체적인 기능만 담당합니다.
 */

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    private final AwsS3Service awsS3Service;

    public CardResponseDto searchCard(CardRequestDto.CardSearchDto cardSearchDto) {
        Card card = cardRepository.findFirstByCompanyAndCardNameContaining(cardSearchDto.company(), cardSearchDto.cardName())
                .orElseThrow(
                        () -> new GeneralException(ErrorStatus.CARD_NOT_FOUND));

        return CardConverter.toDto(card);
    }

    @Transactional
    public CardResponseDto registerCard(MultipartFile multipartFile, CardRequestDto.CardRegisterDto cardRegisterDto) {
        String image = awsS3Service.uploadFile(multipartFile);

        Card card = CardConverter.toEntity(cardRegisterDto, image);
        cardRepository.save(card);

        return CardConverter.toDto(card);
    }

    @Transactional
    public void deleteCard(Long cardId) {
        Card card = cardRepository.findById(cardId).orElseThrow(
                () -> new GeneralException(ErrorStatus.CARD_NOT_FOUND));

        cardRepository.delete(card);
    }

    public CardResponseDto findById(Long cardId) {
        return CardConverter.toDto(cardRepository.findById(cardId).orElseThrow(() ->
                new GeneralException(ErrorStatus.CARD_NOT_FOUND)));
    }

    public List<CardResponseDto> findAll() {
        return cardRepository.findAll().stream()
                .map(CardConverter::toDto)
                .collect(Collectors.toList());
    }
}
