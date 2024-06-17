package submeet.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.geo.Point;
import submeet.backend.entity.common.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberChat extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;
    private Boolean status;

    @OneToMany(mappedBy = "memberChat")
    List<ChatMessage> messageList = new ArrayList<>();

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
