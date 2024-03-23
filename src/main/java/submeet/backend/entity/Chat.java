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
public class Chat {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private int post_id;

    @Column
    private Timestamp appointment_time;
}
