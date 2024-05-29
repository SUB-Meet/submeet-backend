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
    }
}
