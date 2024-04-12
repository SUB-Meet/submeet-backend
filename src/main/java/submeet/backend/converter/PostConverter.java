package submeet.backend.converter;

import org.springframework.data.domain.Page;
import submeet.backend.entity.Post;
import submeet.backend.web.dto.post.PostResponseDTO;

public class PostConverter {
    public static PostResponseDTO.PostRegisterResultDTO toRegisterResultDTO(Post post){
        return PostResponseDTO.PostRegisterResultDTO.builder()
                .title(post.getTitle())
                .id(post.getId())
                .build();
    }

    public static Page<PostResponseDTO.PostDTO> toPostPageDTO(Page<Post> postPage) {
        return postPage.map(p -> new PostResponseDTO.PostDTO(
                p.getId(),
                p.getTitle(),
                p.getPostStartStation().getStation().getName(),
                p.getPostEndStation().getStation().getName(),
                p.getCategory(),
                p.getAge_range(),
                p.getCreatedAt(),
                p.getStart_time(),
                p.getEnd_time(),
                p.getParticipants(),
                p.getCurrent_participants(),
                p.getDescription(),
                p.getGender(),
                p.getStatus()
        ));

    }
}
