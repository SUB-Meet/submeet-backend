package submeet.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;
import submeet.backend.entity.common.BaseEntity;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Station extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; //전철역 명
    @Column(columnDefinition = "point")
    private Point location; // 역 좌표
    private String stationCode; //전철역 코드
    private String externalCode; //외부 코드
    private String line; //호선 정보

    public void setLocation(Point location) {
        this.location = location;
    }
}
