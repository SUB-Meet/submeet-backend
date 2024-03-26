package submeet.backend.entity;


import jakarta.persistence.*;
import lombok.*;
import submeet.backend.entity.common.BaseEntity;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String nickName;

    @Column
    private String profile_image;

    @Column
    private int age;
}
