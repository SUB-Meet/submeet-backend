package submeet.backend.apiPayLoad.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import submeet.backend.apiPayLoad.code.BaseCode;
import submeet.backend.apiPayLoad.code.BaseErrorCode;
import submeet.backend.apiPayLoad.code.ReasonDTO;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {
    //일반적인 응답
    _OK(HttpStatus.OK, "COMMON200","success"),

    //멤버 관련 응답
    MEMBER_JOIN(HttpStatus.OK, "MEMBER2001", "member joined"),
    //게시글 관련 응답
    POST_REGISTER(HttpStatus.OK, "POST2001", "post registered"),
    //역 관련 응답
    STATION_INSERT(HttpStatus.OK, "STATION2001", "station inserted to DB");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .isSuccess(true)
                .code(code)
                .message(message)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .httpStatus(httpStatus)
                .build();
    }
}
