package submeet.backend.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import submeet.backend.apiPayLoad.ApiResponse;
import submeet.backend.apiPayLoad.code.status.SuccessStatus;
import submeet.backend.converter.PostConverter;
import submeet.backend.entity.Post;
import submeet.backend.service.post.PostCommandService;
import submeet.backend.web.dto.post.PostRequestDTO;
import submeet.backend.web.dto.post.PostResponseDTO;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {
    private final PostCommandService postCommandService;
    @PostMapping("/")
    public ApiResponse<PostResponseDTO.PostRegisterResultDTO> register(@RequestBody PostRequestDTO.PostRegisterDTO postRegisterDTO){
        Post post = postCommandService.register(postRegisterDTO);
        PostResponseDTO.PostRegisterResultDTO registerResultDTO = PostConverter.toRegisterResultDTO(post);
        return ApiResponse.of(SuccessStatus.POST_REGISTER,registerResultDTO);
    }
}
