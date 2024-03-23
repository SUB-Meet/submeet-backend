package submeet.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String description;
    @Column
    private int departure;
    @Column
    private int destination;
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
