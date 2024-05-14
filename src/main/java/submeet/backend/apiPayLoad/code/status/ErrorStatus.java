package submeet.backend.apiPayLoad.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import submeet.backend.apiPayLoad.code.BaseErrorCode;
import submeet.backend.apiPayLoad.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {
    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // 멤버 관려 예외
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "no member!"),
    // 역 관련 예외
    STATION_NOT_FOUND(HttpStatus.BAD_REQUEST, "STATION4001", "no station!"),
    // 페이지 에러
    PAGE_ERROR(HttpStatus.BAD_REQUEST, "PAGE4001", "page error!"),
    // 게시글 관련 에러
    POST_NOT_FOUND(HttpStatus.BAD_REQUEST, "POST4001", "no post!"),
    // 채팅방 관련 에러
    CHAT_ROOM_NOT_FOUND(HttpStatus.NOT_FOUND, "CHAT4001","no room!"),
    MEMBER_CHAT_NOT_FOUND(HttpStatus.NOT_FOUND,"CHAT4002","no member chat!"),
    ALREADY_EXIST_MEMBER_CHAT(HttpStatus.BAD_REQUEST, "CHAT4003", "already exist"),
    NO_ACCESS_MEMBER_CHAT(HttpStatus.BAD_REQUEST, "CHAT4004", "채팅방에 입장중인 상태가 아닙니다."),
    ;
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
