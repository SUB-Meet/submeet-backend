package submeet.backend.web.dto.station;

import lombok.Data;
import lombok.Getter;

import java.util.List;

public class StationRequestDTO {
    @Data
    public static class StationDataInsertDTO{
        private List<StationData> DATA;
        @Data
        public static class StationData{
            private String line_num;
            private String station_cd;
            private String station_nm;
            private String fr_code;
        }
    }
//    @Data
//    public static class StationSpatialDataInsertDTO{
//        @Data
//        public static class StationSpatialData{
//            private String crdnt_x;
//            private String crdnt_y;
//            private String statn_id;
//            private String
//        }
//    }
}
