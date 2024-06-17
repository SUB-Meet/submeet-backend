package submeet.backend.service.post;

import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import submeet.backend.apiPayLoad.code.status.ErrorStatus;
import submeet.backend.apiPayLoad.exception.handler.PostHandler;
import submeet.backend.entity.Post;
import submeet.backend.entity.PostStartStation;
import submeet.backend.entity.Station;
import submeet.backend.repository.PostRepository;
import submeet.backend.repository.PostStartStationRepository;
import submeet.backend.repository.StationRepository;
import submeet.backend.web.dto.post.PostRequestDTO;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostQueryServiceImpl implements PostQueryService{
    private final PostRepository postRepository;
    private final StationRepository stationRepository;
    private final PostStartStationRepository postStartStationRepository;
    @Override
    public Page<Post> spatialSearch(PostRequestDTO.PostSpatialSearchDTO postSpatialSearchDTO, Integer page) {
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
        Coordinate topLeftCoordinate = new Coordinate(postSpatialSearchDTO.getTopLeftLongitude(), postSpatialSearchDTO.getTopLeftLatitude());
        Coordinate topRightCoordinate = new Coordinate(postSpatialSearchDTO.getTopRightLongitude(), postSpatialSearchDTO.getTopRightLatitude());
        Coordinate bottomLeftCoordinate = new Coordinate(postSpatialSearchDTO.getBottomLeftLongitude(), postSpatialSearchDTO.getBottomLeftLatitude());
        Coordinate bottomRightCoordinate = new Coordinate(postSpatialSearchDTO.getBottomRightLongitude(), postSpatialSearchDTO.getBottomRightLatitude());
        Coordinate[] coordinates = {topLeftCoordinate, topRightCoordinate, bottomRightCoordinate, bottomLeftCoordinate, topLeftCoordinate};
        Polygon polygon = geometryFactory.createPolygon(coordinates);
        return postRepository.findPostByLocation(polygon, PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "createdAt")));
    }

    /**
     * 역 이름으로 역을 조회
     * 역 리스트로 POSTSTARTSTATION 조회
     *
     * @param stationName
     * @return
     */
    @Override
    public List<Post> searchByStationName(String stationName, Integer page) {
        List<Post> postList = new ArrayList<>();
        List<Station> stationList = stationRepository.findByName(stationName);
        for (Station station : stationList) {
            Page<PostStartStation> postStartStationList = postStartStationRepository.findByStation(station, PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "createdAt")));
            List<Post> postListInStartStation = postStartStationList.stream()
                    .map(PostStartStation::getPost)
                    .toList();
            postList.addAll(postListInStartStation);
        }
        return postList;
    }

    @Override
    public Post findById(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new PostHandler(ErrorStatus.POST_NOT_FOUND));
    }
}
