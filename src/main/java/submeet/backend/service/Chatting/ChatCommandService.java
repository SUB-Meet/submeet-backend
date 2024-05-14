package submeet.backend.service.Chatting;

import submeet.backend.entity.ChatRoom;
import submeet.backend.entity.Member;
import submeet.backend.web.dto.chat.ChatRequestDTO;

public interface ChatCommandService {
    public ChatRoom createChatRoom(ChatRequestDTO.ChatCreateResponseDTO chatCreateResponseDTO);
    public void plusUserCnt(Long roomId);
    public void minusUserCnt(Long roomId);
    public Member addMember(Long roomId, Long memberId);
    public Member delMember(Long roomId, Long memberId);
}
