package submeet.backend.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import submeet.backend.apiPayLoad.ApiResponse;
import submeet.backend.apiPayLoad.code.status.SuccessStatus;
import submeet.backend.converter.PostConverter;
import submeet.backend.converter.StationConverter;
import submeet.backend.entity.Post;
import submeet.backend.entity.Station;
import submeet.backend.service.post.PostCommandService;
import submeet.backend.service.post.PostQueryService;
import submeet.backend.validation.annotation.CheckPage;
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

    /**
     * 게시물 등록
     * @param postRegisterDTO
     * @return
     */
    @PostMapping
    public ApiResponse<PostResponseDTO.PostRegisterResultDTO> register(@RequestBody PostRequestDTO.PostRegisterDTO postRegisterDTO){
        Post post = postCommandService.register(postRegisterDTO);
        PostResponseDTO.PostRegisterResultDTO registerResultDTO = PostConverter.toRegisterResultDTO(post);
        return ApiResponse.of(SuccessStatus.POST_REGISTER,registerResultDTO);
    }


    @GetMapping
    public ApiResponse<PostResponseDTO.PostSearchByStationNameDTO> searchByName(@CheckPage @RequestParam(name = "page") Integer page,@RequestParam("station_name") String stationName ){
        List<Post> postList = postQueryService.searchByStationName(stationName, page);
        PostResponseDTO.PostSearchByStationNameDTO postSearchByNameDTO =PostConverter.toPostSearchByStationNameDTO(postList);
        return ApiResponse.of(SuccessStatus.POST_NAME_SEARCH, postSearchByNameDTO);
    }

    @PostMapping("/spatial")
    public ApiResponse<Page<PostResponseDTO.PostDTO>> spatialSearch(@CheckPage @RequestParam(name = "page") Integer page, @RequestBody PostRequestDTO.PostSpatialSearchDTO postSpatialSearchDTO){
        Page<Post> postPage = postQueryService.spatialSearch(postSpatialSearchDTO, page);
        Page<PostResponseDTO.PostDTO> postDTOPage = PostConverter.toPostPageDTO(postPage);
        return ApiResponse.of(SuccessStatus.POST_SPATIAL_SEARCH, postDTOPage);
    }
}
