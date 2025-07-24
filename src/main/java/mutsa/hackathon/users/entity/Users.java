package mutsa.hackathon.users.entity;

import jakarta.persistence.*;
import lombok.*;
import mutsa.hackathon.match.entity.Matching;
import mutsa.hackathon.oauth.entity.RefreshToken;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate birth;

    private String destination;

    private BigDecimal rate;

    private int coin;

    private String email;


    @OneToOne
    @JoinColumn(name = "tokenId")
    private RefreshToken refreshToken;

    @OneToOne(mappedBy = "user")
    private Matching match;

    // 성별 enum 정의 (내부 or 외부 클래스)
    public enum Gender {
        MALE, FEMALE
    }
}
