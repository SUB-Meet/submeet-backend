package submeet.backend.converter;

import submeet.backend.entity.Post;
import submeet.backend.web.dto.post.PostResponseDTO;

public class PostConverter {
    public static PostResponseDTO.PostRegisterResultDTO toRegisterResultDTO(Post post){
        return PostResponseDTO.PostRegisterResultDTO.builder()
                .title(post.getTitle())
                .id(post.getId())
                .build();
    }
}
