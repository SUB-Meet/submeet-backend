package submeet.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import submeet.backend.entity.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage,Long> {
}
