package submeet.backend.service.Chatting;

import submeet.backend.entity.ChatMessage;
import submeet.backend.web.dto.chat.ChatMessageDTO;

public interface MessageCommandService {
    public ChatMessage save(ChatMessageDTO chatMessageDTO);

}
