package submeet.backend.service.post;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import submeet.backend.apiPayLoad.code.status.ErrorStatus;
import submeet.backend.apiPayLoad.exception.handler.MemberHandler;
import submeet.backend.apiPayLoad.exception.handler.StationHandler;
import submeet.backend.entity.*;
import submeet.backend.repository.*;
import submeet.backend.security.TokenService;
import submeet.backend.service.Chatting.ChatCommandService;
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
    private final TokenService tokenService;
    private final MemberRepository memberRepository;
    private final ChatCommandService chatCommandService;
    @Override
    public Post register(PostRequestDTO.PostRegisterDTO postRegisterDTO, HttpServletRequest httpServletRequest) {
        // 작성한사람 가져오기
        String memberEmail = tokenService.getUid(tokenService.getJwtFromHeader(httpServletRequest));
        Member member = memberRepository.findByEmail(memberEmail).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        //시작역, 출발역 등록
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
                .writer(member)
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
