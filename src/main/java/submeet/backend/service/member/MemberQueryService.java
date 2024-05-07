package submeet.backend.service.member;

import submeet.backend.entity.Member;

public interface MemberQueryService {
    public Member findMember(Long memberId);
}
