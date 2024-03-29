package submeet.backend.service.member;

import submeet.backend.entity.Member;
import submeet.backend.web.dto.member.MemberRequestDTO;

public interface MemberService {
    Member join(MemberRequestDTO.MemberJoinDTO memberJoinDTO);
}
