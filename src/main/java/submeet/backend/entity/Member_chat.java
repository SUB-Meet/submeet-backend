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
public class Member_chat {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Long member_id;
    @Column
    private Long chat_id;
    @Column
    private String location;
}
