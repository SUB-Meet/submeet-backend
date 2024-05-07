package submeet.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import submeet.backend.entity.ChatRoom;
import submeet.backend.entity.Member;
import submeet.backend.entity.MemberChat;

public interface MemberChatRepository extends JpaRepository<MemberChat, Long> {
    public MemberChat findByMemberAndChatRoom(Member member, ChatRoom chatRoom);
}
