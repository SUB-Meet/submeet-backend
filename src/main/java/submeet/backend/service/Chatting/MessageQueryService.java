package submeet.backend.service.Chatting;

import org.springframework.data.domain.Page;
import submeet.backend.entity.ChatMessage;
import submeet.backend.entity.MemberChat;

public interface MessageQueryService {
    public Page<ChatMessage> findChatMessageByMemberChat(MemberChat memberChat, Integer page);
}
