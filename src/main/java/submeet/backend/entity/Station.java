package submeet.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.geo.Point;
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

    @Column
    private String name;

    @Column
    private Point location;
}
