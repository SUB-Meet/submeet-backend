package submeet.backend.apiPayLoad.exception.handler;

import submeet.backend.apiPayLoad.code.BaseErrorCode;
import submeet.backend.apiPayLoad.exception.GeneralException;

public class MemberHandler extends GeneralException {
    public MemberHandler(BaseErrorCode code) {
        super(code);
    }
}
