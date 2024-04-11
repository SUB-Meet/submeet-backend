package submeet.backend.service.station;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import submeet.backend.apiPayLoad.code.status.ErrorStatus;
import submeet.backend.apiPayLoad.exception.handler.StationHandler;
import submeet.backend.entity.Station;
import submeet.backend.repository.StationRepository;
import submeet.backend.web.dto.station.StationRequestDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class StationCommandServiceImpl implements StationCommandService{
    private final StationRepository stationRepository;
    @Override
    public List<Station> insertData(StationRequestDTO.StationDataInsertDTO stationDataInsertDTO) {

        List<StationRequestDTO.StationDataInsertDTO.StationData> stationData = stationDataInsertDTO.getData();


        return stationData.stream()
                .map(s -> Station.builder()
                        .name(s.getStation_nm())
                        .stationCode(s.getStation_cd())
                        .externalCode(s.getFr_code())
                        .line(s.getLine_num())
                        .build()
                )
                .map(stationRepository::save)
                .toList();
    }

    @Override
    public List<Station> insertSpatialData(StationRequestDTO.StationSpatialDataInsertDTO stationSpatialDataInsertDTO) {
        List<StationRequestDTO.StationSpatialDataInsertDTO.StationSpatialData> spatialDataList = stationSpatialDataInsertDTO.getData();
        List<Station> stationList = new ArrayList<>();

        for (StationRequestDTO.StationSpatialDataInsertDTO.StationSpatialData spatialData  : spatialDataList) {
            log.info("name : {}",spatialData.getName());

            if (spatialData.getLat() == null || spatialData.getLng() == null) {
                log.warn("Station [{}] has missing latitude or longitude. Skipping...", spatialData.getName());
                continue;
            }

            Station station = stationRepository.findByNameAndLine(spatialData.getName(), spatialData.getLine()).orElseThrow(() -> new StationHandler(ErrorStatus.STATION_NOT_FOUND));
            GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
            Point point = geometryFactory.createPoint(new Coordinate(spatialData.getLng(),spatialData.getLat()));
            point.setSRID(4326);
            station.setLocation(point);
            stationList.add(station);
        }
        return stationList;
    }

    public static String codeToString(Long code){
        if (code == null) {
            return "";
        }

        String strNum = String.valueOf(code);
        int desiredLength = 4;

        if (strNum.length() >= desiredLength) {
            return strNum;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("0".repeat(desiredLength - strNum.length()));
        sb.append(strNum);

        return sb.toString();
    }
}
