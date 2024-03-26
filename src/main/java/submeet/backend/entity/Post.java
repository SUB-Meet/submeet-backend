package submeet.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import submeet.backend.entity.common.BaseEntity;

import java.sql.Timestamp;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String description;

    @OneToOne
    @JoinColumn(name = "departure_station_id")
    private Station departureStation;
    @OneToOne
    @JoinColumn(name = "destination_station_id")
    private Station destinationStation;
    @Column
    private String title;

    @Column
    private Timestamp start_time;

    @Column
    private Timestamp end_time;

    @Column
    private String category;

    @Column
    private int participants;

    @Column
    private int current_participants;

    @Column
    private int gender;

    @Column
    private int age_range;
}
