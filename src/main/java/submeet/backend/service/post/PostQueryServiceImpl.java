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
import submeet.backend.entity.Post;
import submeet.backend.repository.PostRepository;
import submeet.backend.web.dto.post.PostRequestDTO;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostQueryServiceImpl implements PostQueryService{
    private final PostRepository postRepository;
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
}
