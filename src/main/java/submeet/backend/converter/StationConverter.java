package submeet.backend.converter;

import submeet.backend.entity.Station;
import submeet.backend.web.dto.station.StationRequestDTO;
import submeet.backend.web.dto.station.StationResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class StationConverter {
    public static StationResponseDTO.StationInsertResultDTO toInsertResultDTO(List<Station> stations){
        List<String> stationNames = stations.stream()
                .map(Station::getName)
                .toList();
        return StationResponseDTO.StationInsertResultDTO.builder()
                .station_name_list(stationNames)
                .build();
    }

    public static StationResponseDTO.StationSpatialSearchResultDTO toSpatialSearchResultDTO(List<Station> stationList) {
        List<StationResponseDTO.StationDTO> stationDTOList = stationList.stream()
                .map(s -> StationResponseDTO.StationDTO.builder()
                        .station_id(s.getId())
                        .station_code(s.getStationCode())
                        .station_name(s.getName())
                        .lat(s.getLocation().getY())
                        .lng(s.getLocation().getX())
                        .line(s.getLine())
                        .build())
                .toList();

        return StationResponseDTO.StationSpatialSearchResultDTO.builder()
                .station_list(stationDTOList)
                .build();

    }

    public static StationResponseDTO.StationLocationSearchResultDTO toStationLocationSearchResultDTO(List<Station> stationList) {
        List<StationResponseDTO.StationLocationDTO> stationLocationDTOList = stationList.stream()
                .map(station -> StationResponseDTO.StationLocationDTO.builder()
                        .station_id(station.getId())
                        .station_line(station.getLine())
                        .station_name(station.getName())
                        .latitude(station.getLocation().getY())
                        .longitude(station.getLocation().getX())
                        .build()
                ).toList();
        return StationResponseDTO.StationLocationSearchResultDTO.builder()
                .stationLocationList(stationLocationDTOList)
                .build();
    }
}
