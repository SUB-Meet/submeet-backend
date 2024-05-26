package submeet.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import submeet.backend.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByEmail(String email);
    Boolean existsByEmail(String email);
}
