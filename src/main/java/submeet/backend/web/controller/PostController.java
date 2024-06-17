package submeet.backend.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import submeet.backend.apiPayLoad.ApiResponse;
import submeet.backend.apiPayLoad.code.status.ErrorStatus;
import submeet.backend.apiPayLoad.code.status.SuccessStatus;
import submeet.backend.apiPayLoad.exception.handler.PostHandler;
import submeet.backend.converter.PostConverter;
import submeet.backend.converter.StationConverter;
import submeet.backend.entity.ChatRoom;
import submeet.backend.entity.Member;
import submeet.backend.entity.Post;
import submeet.backend.entity.Station;
import submeet.backend.security.TokenService;
import submeet.backend.service.Chatting.ChatCommandService;
import submeet.backend.service.post.PostCommandService;
import submeet.backend.service.post.PostQueryService;
import submeet.backend.validation.annotation.CheckPage;
import submeet.backend.web.dto.chat.ChatRequestDTO;
import submeet.backend.web.dto.post.PostRequestDTO;
import submeet.backend.web.dto.post.PostResponseDTO;
import submeet.backend.web.dto.station.StationRequestDTO;
import submeet.backend.web.dto.station.StationResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {
    private final PostCommandService postCommandService;
    private final PostQueryService postQueryService;
    private final ChatCommandService chatCommandService;
    private final TokenService tokenService;

    /**
     * 게시물 등록
     * @param postRegisterDTO
     * @return
     */
    @PostMapping
    public ApiResponse<PostResponseDTO.PostRegisterResultDTO> register(@RequestBody PostRequestDTO.PostRegisterDTO postRegisterDTO, HttpServletRequest httpServletRequest){
        Post post = postCommandService.register(postRegisterDTO,httpServletRequest);

        ChatRequestDTO.ChatCreateRequestDTO chatCreatRequestDTO = ChatRequestDTO.ChatCreateRequestDTO.builder()
                .post_id(post.getId())
                .appointment_time(post.getStart_time())
                .build();
        chatCommandService.createChatRoom(chatCreatRequestDTO);

        PostResponseDTO.PostRegisterResultDTO registerResultDTO = PostConverter.toRegisterResultDTO(post);
        return ApiResponse.of(SuccessStatus.POST_REGISTER,registerResultDTO);
    }


    @GetMapping
    public ApiResponse<PostResponseDTO.PostSearchByStationNameDTO> searchByName(@CheckPage @RequestParam(name = "page") Integer page,@RequestParam("station_name") String stationName ){
        List<Post> postList = postQueryService.searchByStationName(stationName, page);
        PostResponseDTO.PostSearchByStationNameDTO postSearchByNameDTO =PostConverter.toPostSearchByStationNameDTO(postList);
        return ApiResponse.of(SuccessStatus.POST_NAME_SEARCH, postSearchByNameDTO);
    }

    @DeleteMapping
    public ApiResponse<PostResponseDTO.PostDeleteResultDTO> delete(@RequestParam(name = "post_id") Long postId, HttpServletRequest httpServletRequest){
        Post post = postQueryService.findById(postId);
        Member writer = post.getWriter();
        String token = tokenService.getJwtFromHeader(httpServletRequest);
        String memberEmail = tokenService.getUid(token);
        if(writer.getEmail().equals(memberEmail)){
            postCommandService.delete(post);
        }else{
            throw new PostHandler(ErrorStatus.NO_DELETE_AUTHORIZATION);
        }
        PostResponseDTO.PostDeleteResultDTO result = PostResponseDTO.PostDeleteResultDTO.builder()
                .post_id(post.getId())
                .build();

        return ApiResponse.of(SuccessStatus.POST_DELETE,result);
    }

    @PostMapping("/spatial")
    public ApiResponse<Page<PostResponseDTO.PostDTO>> spatialSearch(@CheckPage @RequestParam(name = "page") Integer page, @RequestBody PostRequestDTO.PostSpatialSearchDTO postSpatialSearchDTO){
        Page<Post> postPage = postQueryService.spatialSearch(postSpatialSearchDTO, page);
        Page<PostResponseDTO.PostDTO> postDTOPage = PostConverter.toPostPageDTO(postPage);
        return ApiResponse.of(SuccessStatus.POST_SPATIAL_SEARCH, postDTOPage);
    }
}
