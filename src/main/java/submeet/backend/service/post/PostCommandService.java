package submeet.backend.service.post;

import jakarta.servlet.http.HttpServletRequest;
import submeet.backend.entity.Post;
import submeet.backend.web.dto.post.PostRequestDTO;

public interface PostCommandService {
    public Post register(PostRequestDTO.PostRegisterDTO postRegisterDTO, HttpServletRequest httpServletRequest);
}
