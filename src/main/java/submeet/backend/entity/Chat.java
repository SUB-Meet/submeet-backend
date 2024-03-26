package submeet.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import submeet.backend.entity.common.BaseEntity;

import java.security.Timestamp;


@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Chat extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Column
    private Timestamp appointment_time;
}
