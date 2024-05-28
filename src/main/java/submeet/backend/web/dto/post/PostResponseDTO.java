package submeet.backend.web.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class PostResponseDTO {
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class PostRegisterResultDTO{
        private Long id;
        private Long chat_room_id;
        private Long member_id;
        private String title;
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class PostDTO{
        private Long post_id;
        private Long member_id;
        private Long chat_room_id;
        private String post_title;
        private String departure_station;
        private String departure_station_line;
        private String destination_station;
        private String destination_station_line;
        private String category;
        private int age_range;
        private LocalDateTime created_at;
        private LocalDateTime start_time;
        private LocalDateTime end_time;
        private int participants;
        private int current_participants;
        private String description;
        private int gender;
        private int status;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PostSearchByStationNameDTO {
        List<PostDTO> postList;
    }
}
