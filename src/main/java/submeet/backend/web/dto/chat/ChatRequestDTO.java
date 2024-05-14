package submeet.backend.web.dto.chat;

import lombok.Getter;

import java.time.LocalDateTime;

public class ChatRequestDTO {
    @Getter
    public static class ChatCreateResponseDTO{
        Long post_id;
        LocalDateTime appointment_time;
    }
}
