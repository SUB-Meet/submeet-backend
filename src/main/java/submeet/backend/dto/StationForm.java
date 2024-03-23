package submeet.backend.dto;

import jakarta.persistence.Column;
import submeet.backend.entity.Post;
import submeet.backend.entity.Station;

public class StationForm {

    private String name;

    private String location;

    public Station toEntity(){
        return new Station(null,name,location);
    }

}
