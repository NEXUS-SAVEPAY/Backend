package savepay.savepay.global.code;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ErrorReasonDto {
    private final HttpStatus httpStatus;
    private final boolean isSuccess;
    private final String code;
    private final String message;

    public boolean getIsSuccess() {
        return isSuccess;
    }
}
