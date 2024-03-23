package submeet.backend.dto;

import jakarta.persistence.Column;
import submeet.backend.entity.Chat;

import java.sql.Timestamp;

public class ChatForm {
    private int post_id;

    private Timestamp appointment_time;

    public Chat toEntity(){
        return new Chat(null,post_id,appointment_time);
    }
}
