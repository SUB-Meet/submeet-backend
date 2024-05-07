package submeet.backend.converter;

import submeet.backend.entity.ChatRoom;
import submeet.backend.web.dto.chat.ChatResponseDTO;

public class ChatConverter {
    public static ChatResponseDTO.ChatCreateResultDTO toChatCreateResultDTO(ChatRoom chatRoom){
        return ChatResponseDTO.ChatCreateResultDTO.builder()
                .chat_room_id(chatRoom.getId())
                .build();
    }
}
