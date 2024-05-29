package submeet.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import submeet.backend.entity.ChatRoom;
import submeet.backend.entity.Member;
import submeet.backend.entity.MemberChat;

import java.util.List;
import java.util.Optional;

public interface MemberChatRepository extends JpaRepository<MemberChat, Long> {
    public Optional<MemberChat> findByMemberAndChatRoom(Member member, ChatRoom chatRoom);
    public boolean existsByMemberAndChatRoom(Member member, ChatRoom chatRoom);
    public List<MemberChat> findByChatRoom(ChatRoom chatRoom);
    public List<MemberChat> findByMember(Member member);
}
