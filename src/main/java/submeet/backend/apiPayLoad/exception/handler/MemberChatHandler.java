package submeet.backend.apiPayLoad.exception.handler;

import submeet.backend.apiPayLoad.code.BaseErrorCode;
import submeet.backend.apiPayLoad.exception.GeneralException;

public class MemberChatHandler extends GeneralException {
    public MemberChatHandler(BaseErrorCode code) {
        super(code);
    }
}
