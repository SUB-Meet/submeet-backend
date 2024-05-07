package submeet.backend.apiPayLoad.exception.handler;

import submeet.backend.apiPayLoad.code.BaseErrorCode;
import submeet.backend.apiPayLoad.exception.GeneralException;

public class ChatHandler extends GeneralException {
    public ChatHandler(BaseErrorCode code) {
        super(code);
    }
}
