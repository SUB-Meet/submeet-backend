package submeet.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import submeet.backend.entity.common.BaseEntity;

import java.time.LocalDateTime;


@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoom extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private Post post;

    @Column
    private LocalDateTime appointmentTime;

    @ColumnDefault("0")
    private Long userCount;

    public void setUserCount(Long userCount) {
        this.userCount = userCount;
    }
}
