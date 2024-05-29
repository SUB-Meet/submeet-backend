package submeet.backend.converter;

import submeet.backend.entity.Member;
import submeet.backend.security.Token;
import submeet.backend.web.dto.member.MemberRequestDTO;
import submeet.backend.web.dto.member.MemberResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
                .nick_name(member.getNickName())
                .email(member.getEmail())
                .profile_image(member.getProfile_image())
                .gender(member.getGender())
                .age(member.getAge())
                .build();
    }

    public static MemberResponseDTO.MemberDTO toMemberResponseDTO(Member member){
        return MemberResponseDTO.MemberDTO.builder()
                .member_id(member.getId())
                .nick_name(member.getNickName())
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

    public static List<MemberResponseDTO.MemberDTO> toMemberDTOList(List<Member> memberList) {
        return memberList.stream()
                .map(
                        m -> {
                            return MemberResponseDTO.MemberDTO.builder()
                                    .member_id(m.getId())
                                    .age(m.getAge())
                                    .nick_name(m.getNickName())
                                    .email(m.getEmail())
                                    .profile_image(m.getProfile_image())
                                    .gender(m.getGender())
                                    .build();
                        }
                )
                .collect(Collectors.toList());
    }
}
