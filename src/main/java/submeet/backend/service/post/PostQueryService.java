package submeet.backend.service.post;

import org.springframework.data.domain.Page;
import submeet.backend.entity.Post;
import submeet.backend.web.dto.post.PostRequestDTO;

import java.util.List;

public interface PostQueryService {
    Page<Post> spatialSearch(PostRequestDTO.PostSpatialSearchDTO postSpatialSearchDTO, Integer page);
}
