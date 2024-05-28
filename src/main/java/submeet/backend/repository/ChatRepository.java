package submeet.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import submeet.backend.entity.ChatRoom;
import submeet.backend.entity.Post;

import java.util.Optional;

public interface ChatRepository extends JpaRepository<ChatRoom, Long> {
    public Optional<ChatRoom> findByPost(Post post);
}
