package submeet.backend.converter;

import org.springframework.data.domain.Page;
import submeet.backend.entity.ChatMessage;
import submeet.backend.entity.ChatRoom;
import submeet.backend.entity.MemberChat;
import submeet.backend.web.dto.chat.ChatResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ChatConverter {
    public static ChatResponseDTO.ChatCreateResultDTO toChatCreateResultDTO(ChatRoom chatRoom){
        return ChatResponseDTO.ChatCreateResultDTO.builder()
                .chat_room_id(chatRoom.getId())
                .build();
    }

    public static List<ChatResponseDTO.ChatRoomDTO> toChatDTOList(List<ChatRoom> chatList) {
        return chatList.stream()
                .map(c -> {return ChatResponseDTO.ChatRoomDTO.builder()
                        .chat_room_id(c.getId())
                        .appointment_time(c.getAppointmentTime())
                        .post_id(c.getPost().getId())
                        .user_count(c.getUserCount())
                        .post_title(c.getPost().getTitle())
                        .category(c.getPost().getCategory())
                        .build();}
                )
                .collect(Collectors.toList());
    }
}
