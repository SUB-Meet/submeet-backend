package submeet.backend.web.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberRequestDTO {
    @Getter
    public static class MemberJoinDTO {
        private String nick_name;
        private String profile_image;
        private String email;
        private int age;
        private Boolean gender;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MemberDTO{
        private Long id;
        private String nick_name;
        private String profile_image;
        private String email;
        private int age;
        private Boolean gender;
    }
}
