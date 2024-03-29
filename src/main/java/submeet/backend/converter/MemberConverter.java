package submeet.backend.converter;

import submeet.backend.entity.Member;
import submeet.backend.web.dto.member.MemberResponseDTO;

public class MemberConverter {

    public static MemberResponseDTO.MemberJoinResultDTO toMemberJoinResultDTO(Member member) {
        return MemberResponseDTO.MemberJoinResultDTO.builder()
                .id(member.getId())
                .build();
    }
}
