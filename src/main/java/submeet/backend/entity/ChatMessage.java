package submeet.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import submeet.backend.entity.common.BaseEntity;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member sender;
    private String type;

    @Column(columnDefinition = "TEXT")
    private String message;
}