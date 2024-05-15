package submeet.backend.web.dto.station;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class StationResponseDTO {
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class StationInsertResultDTO{
        private List<String> station_name_list;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class StationSpatialSearchResultDTO {
        private List<StationDTO> station_list;
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class StationDTO{
        private Long station_id;
        private String station_name;
        private String station_code;
        private Double lat;
        private Double lng;
        private String line;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class StationLocationSearchResultDTO{
        List<StationLocationDTO> stationLocationList;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class StationLocationDTO{
        private Long station_id;
        private String station_name;
        private String station_line;
        private double latitude;
        private double longitude;
    }
}
