package submeet.backend.apiPayLoad.exception.handler;

import submeet.backend.apiPayLoad.code.BaseErrorCode;
import submeet.backend.apiPayLoad.exception.GeneralException;

public class PostHandler extends GeneralException {
    public PostHandler(BaseErrorCode code) {
        super(code);
    }
}
