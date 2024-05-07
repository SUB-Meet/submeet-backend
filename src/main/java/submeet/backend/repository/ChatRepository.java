package submeet.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import submeet.backend.entity.ChatRoom;

public interface ChatRepository extends JpaRepository<ChatRoom, Long> {
}
