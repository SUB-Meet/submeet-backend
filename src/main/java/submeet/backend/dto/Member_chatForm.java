package submeet.backend.dto;

import submeet.backend.entity.Member;
import submeet.backend.entity.Member_chat;

public class Member_chatForm {
    // 맴버 입장에서 봤을 대 보이는 체팅룸들
    private Long mamber_id;
    private Long chat_id;
    private String locarion;

    public Member_chat toEntity(){
        return new Member_chat(null,mamber_id,chat_id,locarion);
    }
}
