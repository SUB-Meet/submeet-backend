package submeet.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import submeet.backend.entity.Post;

public interface PostRepository extends JpaRepository<Post,Long> {
}
