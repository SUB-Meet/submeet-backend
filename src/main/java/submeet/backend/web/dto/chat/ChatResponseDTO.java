package submeet.backend.web.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import submeet.backend.web.dto.member.MemberResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ChatResponseDTO {
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChatCreateResultDTO{
        private Long chat_room_id;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MemberListDTO {
        private List<MemberResponseDTO.MemberDTO> member_list;
    }
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChatListDTO{
        private List<ChatResponseDTO.ChatRoomDTO> chat_list;
    }
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChatRoomDTO {
        private Long chat_room_id;
        private LocalDateTime appointment_time;
        private Long post_id;
        private Long user_count;
        private String category;
        private String post_title;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChatMessageListDTO {
        List<MessageDTO> message_list;
    }
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MessageDTO {
        private Long message_id;
        private LocalDateTime created_at;
        private String message;
        private String type;
        private Long member_id;
        private String profile_image;
        private String nick_name;
        private String email;
    }
}
