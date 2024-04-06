package submeet.backend.service.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import submeet.backend.apiPayLoad.code.status.ErrorStatus;
import submeet.backend.apiPayLoad.exception.handler.StationHandler;
import submeet.backend.entity.Post;
import submeet.backend.entity.PostEndStation;
import submeet.backend.entity.PostStartStation;
import submeet.backend.entity.Station;
import submeet.backend.repository.PostEndStationRepository;
import submeet.backend.repository.PostRepository;
import submeet.backend.repository.PostStartStationRepository;
import submeet.backend.repository.StationRepository;
import submeet.backend.web.dto.post.PostRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PostCommandServiceImpl implements PostCommandService{
    private final StationRepository stationRepository;
    private final PostEndStationRepository postEndStationRepository;
    private final PostStartStationRepository postStartStationRepository;
    private final PostRepository postRepository;
    @Override
    public Post register(PostRequestDTO.PostRegisterDTO postRegisterDTO) {

        Station startStation = stationRepository.findByNameAndLine(postRegisterDTO.getStart_station_name(), postRegisterDTO.getStart_line()).orElseThrow(() -> new StationHandler(ErrorStatus.STATION_NOT_FOUND));
        Station endStation = stationRepository.findByNameAndLine(postRegisterDTO.getEnd_station_name(), postRegisterDTO.getEnd_line()).orElseThrow(() -> new StationHandler(ErrorStatus.STATION_NOT_FOUND));

        Post post = postRepository.save(Post.builder()
                .description(postRegisterDTO.getDescription())
                .title(postRegisterDTO.getTitle())
                .age_range(postRegisterDTO.getAge_range())
                .gender(postRegisterDTO.getGender())
                .category(postRegisterDTO.getCategory())
                .start_time(postRegisterDTO.getStart_time())
                .end_time(postRegisterDTO.getEnd_time())
                .current_participants(postRegisterDTO.getCurrent_participants())
                .participants(postRegisterDTO.getParticipants())
                .status(0)
                .build());
        postStartStationRepository.save(PostStartStation.builder()
                .post(post)
                .station(startStation)
                .build());
        postEndStationRepository.save(PostEndStation.builder()
                .post(post)
                .station(endStation)
                .build());

        return post;
    }
}
