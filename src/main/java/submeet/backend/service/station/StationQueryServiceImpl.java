package submeet.backend.service.station;

import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import submeet.backend.entity.Station;
import submeet.backend.repository.StationRepository;
import submeet.backend.web.dto.station.StationRequestDTO;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StationQueryServiceImpl implements StationQueryService{
    private final StationRepository stationRepository;
    @Override
    public List<Station> spatialSearch(StationRequestDTO.StationSpatialSearchDTO stationSpatialSearchDTO) {
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
        Coordinate topLeftCoordinate = new Coordinate(stationSpatialSearchDTO.getTopLeftLongitude(), stationSpatialSearchDTO.getTopLeftLatitude());
        Coordinate topRightCoordinate = new Coordinate(stationSpatialSearchDTO.getTopRightLongitude(), stationSpatialSearchDTO.getTopRightLatitude());
        Coordinate bottomLeftCoordinate = new Coordinate(stationSpatialSearchDTO.getBottomLeftLongitude(), stationSpatialSearchDTO.getBottomLeftLatitude());
        Coordinate bottomRightCoordinate = new Coordinate(stationSpatialSearchDTO.getBottomRightLongitude(), stationSpatialSearchDTO.getBottomRightLatitude());
        Coordinate[] coordinates = {topLeftCoordinate, topRightCoordinate, bottomRightCoordinate, bottomLeftCoordinate, topLeftCoordinate};
        Polygon polygon = geometryFactory.createPolygon(coordinates);
        return stationRepository.findStationWithLocation(polygon);
    }
}
