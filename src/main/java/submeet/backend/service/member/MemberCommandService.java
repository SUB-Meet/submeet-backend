package submeet.backend.service.member;

import submeet.backend.entity.Member;
import submeet.backend.web.dto.member.MemberRequestDTO;

public interface MemberCommandService {
    Member join(MemberRequestDTO.MemberJoinDTO memberJoinDTO);
}
