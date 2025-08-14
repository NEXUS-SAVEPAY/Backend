package savepay.savepay.domain.usercard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import savepay.savepay.domain.payment.entity.card.converter.CardConverter;
import savepay.savepay.domain.payment.entity.card.dto.CardResponseDto;
import savepay.savepay.domain.payment.entity.card.entity.Card;
import savepay.savepay.domain.payment.entity.card.repository.CardRepository;
import savepay.savepay.domain.userpayment.repository.UserPaymentRepository;
import savepay.savepay.domain.user.entity.User;
import savepay.savepay.domain.userpayment.entity.UserPayment;
import savepay.savepay.global.code.status.ErrorStatus;
import savepay.savepay.global.exception.GeneralException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserCardService {

    private final CardRepository cardRepository;

    private final UserPaymentRepository userPaymentRepository;

    public List<CardResponseDto> findUserCardList(User user) {
        return userPaymentRepository.findUserCardList(user)
                .stream()
                .map(up -> CardConverter.toDto((Card) up.getPayment()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void registerUserCard(Long cardId, User user) {
        Card card = cardRepository.findById(cardId).orElseThrow(
                () -> new GeneralException(ErrorStatus.CARD_NOT_FOUND));

        userPaymentRepository.findByUserAndPayment(user, card).ifPresent(userPayment ->
                {
                    throw new GeneralException(ErrorStatus.DUPLICATE_CARD_REGISTER);
                }
        );

        UserPayment userPayment = UserPayment.createUserPayment(user, card, false);

        userPaymentRepository.save(userPayment);
    }

    @Transactional
    public void deleteUserCard(Long cardId, User user) {
        Card card = cardRepository.findById(cardId).orElseThrow(
                () -> new GeneralException(ErrorStatus.CARD_NOT_FOUND));
        UserPayment userPayment = userPaymentRepository.findByUserAndPayment(user, card).orElseThrow(
                () -> new GeneralException(ErrorStatus.USER_PAYMENT_NOT_FOUND));

        userPaymentRepository.delete(userPayment);
    }
}
