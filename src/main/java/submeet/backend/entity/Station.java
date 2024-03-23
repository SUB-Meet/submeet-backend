package submeet.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Station {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String location;
}
