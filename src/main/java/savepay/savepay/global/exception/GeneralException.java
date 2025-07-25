package savepay.savepay.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import savepay.savepay.global.code.BaseErrorCode;
import savepay.savepay.global.code.ErrorReasonDto;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException{

    private BaseErrorCode code;

    public ErrorReasonDto getErrorReason(){
        return this.code.getReason();
    }

    public ErrorReasonDto getErrorReasonHttpStatus(){
        return this.code.getReasonHttpStatus();
    }
}
