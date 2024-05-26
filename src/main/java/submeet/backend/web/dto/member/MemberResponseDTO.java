package submeet.backend.web.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MemberResponseDTO {
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class MemberJoinResultDTO {
        private Long member_id;
        private String accessToken;
        private String refreshToken;
        private LocalDateTime created_at;
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class LoginResultDTO {
        private Long member_id;
        private String accessToken;
        private String refreshToken;
        private LocalDateTime expiration;
    }
}
