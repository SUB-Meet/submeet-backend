package submeet.backend.web.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberRequestDTO {
    @Getter
    public static class MemberJoinDTO {
        private String name;
        private String nick_name;
        private String profile_image;
        private int age;
    }
}
