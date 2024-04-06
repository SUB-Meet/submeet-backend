package submeet.backend.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import submeet.backend.entity.Member;

public interface MemberRepository extends JpaRepository<Member,Long> {
}
