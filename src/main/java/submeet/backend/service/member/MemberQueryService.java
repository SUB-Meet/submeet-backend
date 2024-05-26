package submeet.backend.service.member;

import jakarta.servlet.http.HttpServletRequest;
import submeet.backend.entity.Member;

public interface MemberQueryService {
    public Member findMember(Long memberId);

    public Member login(HttpServletRequest httpServletRequest);
}
