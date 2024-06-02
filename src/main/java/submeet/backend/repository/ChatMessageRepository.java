package submeet.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import submeet.backend.entity.ChatMessage;
import submeet.backend.entity.MemberChat;

public interface ChatMessageRepository extends JpaRepository<ChatMessage,Long> {
    public Page<ChatMessage> findByMemberChat(MemberChat memberChat, Pageable pageable);
}
