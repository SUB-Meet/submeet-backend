package submeet.backend.dto;

import submeet.backend.entity.Member;

public class MemberForm {
    private String name;
    private String nickname;
    private String profile_image;
    private int age;

    public Member toEntity(){
        return new Member(null,name,nickname,profile_image,age);
    }
}
