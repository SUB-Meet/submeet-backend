package submeet.backend.service.station;

import submeet.backend.entity.Station;
import submeet.backend.web.dto.station.StationRequestDTO;

import java.util.List;

public interface StationQueryService {
    public List<Station> spatialSearch(StationRequestDTO.StationSpatialSearchDTO stationSpatialSearchDTO);
}
