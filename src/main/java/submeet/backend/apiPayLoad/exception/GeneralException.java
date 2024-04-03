package submeet.backend.apiPayLoad.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import submeet.backend.apiPayLoad.code.BaseErrorCode;
import submeet.backend.apiPayLoad.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException{
    private BaseErrorCode code;
    public ErrorReasonDTO getErrorReason() {
        return this.code.getReason();
    }

    public ErrorReasonDTO getErrorReasonHttpStatus(){
        return this.code.getReasonHttpStatus();
    }
}
