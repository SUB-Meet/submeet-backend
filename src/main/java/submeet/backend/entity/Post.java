package submeet.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import submeet.backend.entity.common.BaseEntity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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
    @NotNull
    private String description;
    @NotNull
    private String title;
    private LocalDateTime start_time;
    private LocalDateTime end_time;
    @NotNull
    private String category;
    @NotNull
    private int participants;
    private int current_participants;
    private int gender;
    private int age_range;
    private int status;
    @OneToOne(mappedBy = "post")
    private PostStartStation postStartStation;
    @OneToOne(mappedBy = "post")
    private PostEndStation postEndStation;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    Member writer;
    @OneToOne(mappedBy = "post")
    ChatRoom chatRoom;


    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }
}
