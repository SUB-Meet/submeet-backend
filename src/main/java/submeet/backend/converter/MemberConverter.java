package submeet.backend.converter;

import submeet.backend.entity.Member;
import submeet.backend.security.Token;
import submeet.backend.web.dto.member.MemberRequestDTO;
import submeet.backend.web.dto.member.MemberResponseDTO;

import java.time.LocalDateTime;

public class MemberConverter {

    public static MemberResponseDTO.MemberJoinResultDTO toMemberJoinResultDTO(Member member, Token token) {
        return MemberResponseDTO.MemberJoinResultDTO.builder()
                .member_id(member.getId())
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .created_at(member.getCreatedAt())
                .build();
    }

    public static MemberRequestDTO.MemberDTO toMemberDTO(Member member){
        return MemberRequestDTO.MemberDTO.builder()
                .id(member.getId())
                .email(member.getEmail())
                .profile_image(member.getProfile_image())
                .gender(member.getGender())
                .age(member.getAge())
                .build();
    }

    public static MemberResponseDTO.LoginResultDTO toLoginResultDTO(Member member, Token token, LocalDateTime localDateTime) {
        return MemberResponseDTO.LoginResultDTO.builder()
                .member_id(member.getId())
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .expiration(localDateTime)
                .build();
    }
}
