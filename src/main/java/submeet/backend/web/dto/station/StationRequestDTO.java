package submeet.backend.web.dto.station;

import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.Optional;

public class StationRequestDTO {
    @Data
    public static class StationDataInsertDTO{
        private List<StationData> data;
        @Data
        public static class StationData{
            private String line;
            private String station_code;
            private String location;
            private String name;
            private String external_code;
        }
    }

    @Data
    public static class StationSpatialDataInsertDTO {
        private List<StationSpatialData> data;
        @Data
        public static class StationSpatialData{
            private String line;
            private String name;
            private Long code;
            private Double lat;
            private Double lng;
        }
    }

    @Data
    public static class StationSpatialSearchDTO {
        private Double topLeftLatitude;
        private Double topLeftLongitude;
        private Double topRightLatitude;
        private Double topRightLongitude;
        private Double bottomLeftLatitude;
        private Double bottomLeftLongitude;
        private Double bottomRightLatitude;
        private Double bottomRightLongitude;
    }
}
