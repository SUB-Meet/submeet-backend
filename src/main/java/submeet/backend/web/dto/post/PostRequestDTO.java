package submeet.backend.web.dto.post;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PostRequestDTO {
    @Getter
    public static class PostRegisterDTO{
        private String title;
        private String description;
        private String start_station_name;
        private String start_line;
        private String end_station_name;
        private String end_line;
        private LocalDateTime start_time;
        private LocalDateTime end_time;
        private String category;
        private int participants;
        private int current_participants;
        private int gender;
        private int age_range;
    }
}
