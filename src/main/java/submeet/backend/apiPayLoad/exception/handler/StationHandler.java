package submeet.backend.apiPayLoad.exception.handler;

import submeet.backend.apiPayLoad.code.BaseErrorCode;
import submeet.backend.apiPayLoad.exception.GeneralException;

public class StationHandler extends GeneralException {
    public StationHandler(BaseErrorCode code) {
        super(code);
    }
}
