package submeet.backend.service.station;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import submeet.backend.entity.Station;
import submeet.backend.repository.StationRepository;
import submeet.backend.web.dto.station.StationRequestDTO;

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

        List<StationRequestDTO.StationDataInsertDTO.StationData> stationData = stationDataInsertDTO.getDATA();


        return stationData.stream()
                .map(s -> Station.builder()
                        .name(s.getStation_nm())
                        .station_code(s.getStation_cd())
                        .external_code(s.getFr_code())
                        .line(s.getLine_num())
                        .build()
                )
                .map(stationRepository::save)
                .toList();
    }
}
