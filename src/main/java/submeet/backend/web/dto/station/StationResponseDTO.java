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
}
