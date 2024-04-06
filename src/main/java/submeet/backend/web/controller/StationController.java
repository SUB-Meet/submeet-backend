package submeet.backend.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import submeet.backend.apiPayLoad.ApiResponse;
import submeet.backend.apiPayLoad.code.status.SuccessStatus;
import submeet.backend.converter.StationConverter;
import submeet.backend.entity.Station;
import submeet.backend.service.station.StationCommandService;
import submeet.backend.web.dto.station.StationRequestDTO;
import submeet.backend.web.dto.station.StationResponseDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/station")
public class StationController {
    private final StationCommandService stationCommandService;
    @PostMapping("/")
    public ApiResponse<StationResponseDTO.StationInsertResultDTO> stationDataInsert(@RequestBody StationRequestDTO.StationDataInsertDTO stationDataInsertDTO){
        List<Station> stations = stationCommandService.insertData(stationDataInsertDTO);
        StationResponseDTO.StationInsertResultDTO insertResultDTO = StationConverter.toInsertResultDTO(stations);
        return ApiResponse.of(SuccessStatus.STATION_INSERT,insertResultDTO);
    }
}
