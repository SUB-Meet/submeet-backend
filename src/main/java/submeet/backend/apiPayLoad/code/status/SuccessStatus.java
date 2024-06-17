package submeet.backend.apiPayLoad.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import submeet.backend.apiPayLoad.code.BaseCode;
import submeet.backend.apiPayLoad.code.ReasonDTO;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {
    //일반적인 응답
    _OK(HttpStatus.OK, "COMMON200","success"),

    //멤버 관련 응답
    MEMBER_JOIN(HttpStatus.OK, "MEMBER2001", "member joined"),
    MEMBER_LOGIN(HttpStatus.OK, "MEMBER2002" , "login success!" ),
    MEMBER_FOUND(HttpStatus.OK,"MEMBER2003", "member found!"),
    //게시글 관련 응답
    POST_REGISTER(HttpStatus.OK, "POST2001", "post registered"),
    POST_SPATIAL_SEARCH(HttpStatus.OK, "POST2002", "post spatial search success"),
    POST_NAME_SEARCH(HttpStatus.OK, "POST2003", "post name search success"),
    POST_DELETE(HttpStatus.OK, "POST2004", "delete success"),
    //역 관련 응답
    STATION_INSERT(HttpStatus.OK, "STATION2001", "station inserted to DB"),
    STATION_SPATIAL_INSERT(HttpStatus.OK,"STATION2002","station spatial inserted to DB"),
    STATION_SPATIAL_SEARCH(HttpStatus.OK,"STATION2003","station spatial search success"),
    STATION_LOCATION_SEARCH(HttpStatus.OK, "STATION2004", "station location search success"),
    //토큰 관련 응답
    TOKEN_REFRESHED(HttpStatus.UNAUTHORIZED,"TOKEN2001", "Token Refreshed"),
    //채팅 관련 응답
    CHAT_MEMBERS(HttpStatus.OK,"CHAT2001","find member List success"),
    CHATS_FOUND(HttpStatus.OK,"CHAT2002","find member List success")
    ;

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
