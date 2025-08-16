package savepay.savepay.global.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import savepay.savepay.global.code.BaseErrorCode;
import savepay.savepay.global.code.ErrorReasonDto;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    PAYMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "PAYMENT404", "결제 수단을 찾을 수 없습니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),
    CARD_NOT_FOUND(HttpStatus.NOT_FOUND, "CARD404", "카드를 찾을 수 없습니다."),
    USER_PAYMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_PAYMENT404", "사용자 결제수단을 찾을 수 없습니다."),
    USER_TELECOM_NOT_FOUND(HttpStatus.NOT_FOUND, "USERTELECOM404", "유저와 연결된 통신사를 찾을 수 없습니다."),
    BRAND_NOT_FOUND(HttpStatus.NOT_FOUND, "BRAND404", "브랜드를 찾을 수 없습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER404", "유저를 찾을 수 없습니다."),
    OAUTH2_LOGIN_FAIL(HttpStatus.UNAUTHORIZED, "COMMON401", "OAuth2 인증에 실패했습니다."),
    INVALID_TOKEN(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 토큰입니다."),
    INTERESTED_BRAND_NOT_FOUND(HttpStatus.NOT_FOUND, "INTEREST404", "관심 브랜드를 찾을 수 없습니다."),
    DUPLICATE_INTEREST_BRAND(HttpStatus.CONFLICT, "BRAND409", "이미 관심 브랜드로 등록된 브랜드입니다."),
    S3_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "S34001", "사진 업로드에 실패했습니다."),
    S3_FORMAT(HttpStatus.BAD_REQUEST, "S34002", "잘못된 형식의 파일입니다."),
    S3_EMPTY_FILE(HttpStatus.BAD_REQUEST, "S34003", "업로드할 파일이 없습니다."),
    USER_CARD_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_CARD404", "등록된 카드가 없습니다."),
    USER_PAY_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_PAY404", "등록된 페이가 없습니다."),
    DISCOUNT_NOT_FOUND(HttpStatus.NOT_FOUND, "DISCOUNT404", "등록된 혜택이 없습니다."),
    DUPLICATE_CARD_REGISTER(HttpStatus.BAD_REQUEST, "CARD400", "중복된 카드 등록입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDto getReason() {
        return ErrorReasonDto.builder()
                .message(message)
                .httpStatus(httpStatus)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDto getReasonHttpStatus() {
        return ErrorReasonDto.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
