package submeet.backend.web.dto.chat;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.*;
import org.springframework.stereotype.Service;
import submeet.backend.common.enums.MessageType;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChatMessageDTO {
    private String messageType;
    private Long chatRoomId;
    private Long memberId;
    private String message;
}
