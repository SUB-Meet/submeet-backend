package submeet.backend.converter;

import org.springframework.data.domain.Page;
import submeet.backend.entity.ChatRoom;
import submeet.backend.entity.Post;
import submeet.backend.web.dto.post.PostResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class PostConverter {
    public static PostResponseDTO.PostRegisterResultDTO toRegisterResultDTO(Post post, ChatRoom chatRoom){
        return PostResponseDTO.PostRegisterResultDTO.builder()
                .title(post.getTitle())
                .id(post.getId())
                .chat_room_id(chatRoom.getId())
                .member_id(post.getWriter().getId())
                .build();
    }

    public static Page<PostResponseDTO.PostDTO> toPostPageDTO(Page<Post> postPage) {
        return postPage.map(p -> new PostResponseDTO.PostDTO(
                p.getId(),
                p.getTitle(),
                p.getPostStartStation().getStation().getName(),
                p.getPostStartStation().getStation().getLine(),
                p.getPostEndStation().getStation().getName(),
                p.getPostEndStation().getStation().getLine(),
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

    public static PostResponseDTO.PostSearchByStationNameDTO toPostSearchByStationNameDTO(List<Post> postList) {
        List<PostResponseDTO.PostDTO> postDTOList = postList.stream()
                .map(post -> PostResponseDTO.PostDTO.builder()
                        .post_id(post.getId())
                        .post_title(post.getTitle())
                        .departure_station(post.getPostStartStation().getStation().getName())
                        .departure_station_line(post.getPostStartStation().getStation().getLine())
                        .destination_station(post.getPostEndStation().getStation().getName())
                        .destination_station_line(post.getPostEndStation().getStation().getLine())
                        .category(post.getCategory())
                        .age_range(post.getAge_range())
                        .created_at(post.getCreatedAt())
                        .start_time(post.getStart_time())
                        .end_time(post.getEnd_time())
                        .participants(post.getParticipants())
                        .current_participants(post.getCurrent_participants())
                        .description(post.getDescription())
                        .gender(post.getGender())
                        .status(post.getStatus())
                        .build())
                .toList();
        return PostResponseDTO.PostSearchByStationNameDTO.builder()
                .postList(postDTOList)
                .build();
    }
}
