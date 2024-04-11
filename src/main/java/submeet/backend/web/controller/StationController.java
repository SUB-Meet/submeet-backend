package submeet.backend.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import submeet.backend.apiPayLoad.ApiResponse;
import submeet.backend.apiPayLoad.code.status.SuccessStatus;
import submeet.backend.converter.StationConverter;
import submeet.backend.entity.Station;
import submeet.backend.repository.StationRepository;
import submeet.backend.service.station.StationCommandService;
import submeet.backend.service.station.StationQueryService;
import submeet.backend.web.dto.station.StationRequestDTO;
import submeet.backend.web.dto.station.StationResponseDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/station")
public class StationController {
    private final StationCommandService stationCommandService;
    private final StationQueryService stationQueryService;
    private final StationRepository stationRepository;
    /**
     * 역 정보 DB에 insert
     */
    @PostMapping
    public ApiResponse<StationResponseDTO.StationInsertResultDTO> stationDataInsert(@RequestBody StationRequestDTO.StationDataInsertDTO stationDataInsertDTO){
        List<Station> stations = stationCommandService.insertData(stationDataInsertDTO);
        StationResponseDTO.StationInsertResultDTO insertResultDTO = StationConverter.toInsertResultDTO(stations);
        return ApiResponse.of(SuccessStatus.STATION_INSERT,insertResultDTO);
    }


    /**
     * 역 공간 정보 DB에 insert
     */
    @PatchMapping("/spatial")
    public ApiResponse<StationResponseDTO.StationInsertResultDTO> spatialDataInsert(@RequestBody StationRequestDTO.StationSpatialDataInsertDTO stationSpatialDataInsertDTO){
        List<Station> stations = stationCommandService.insertSpatialData(stationSpatialDataInsertDTO);
        StationResponseDTO.StationInsertResultDTO insertResultDTO = StationConverter.toInsertResultDTO(stations);
        return ApiResponse.of(SuccessStatus.STATION_INSERT,insertResultDTO);
    }
    /**
     * 역 공간 정보 기반 검색
     */
    @PostMapping("/spatial")
    public ApiResponse<StationResponseDTO.StationSpatialSearchResultDTO> spatialSearch(@RequestBody StationRequestDTO.StationSpatialSearchDTO stationSpatialSearchDTO){
        List<Station> stationList = stationQueryService.spatialSearch(stationSpatialSearchDTO);
        StationResponseDTO.StationSpatialSearchResultDTO stationSpatialSearchResultDTO = StationConverter.toSpatialSearchResultDTO(stationList);
        return ApiResponse.of(SuccessStatus.STATION_SPATIAL_SEARCH,stationSpatialSearchResultDTO);
    }


}
