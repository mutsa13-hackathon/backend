package mutsa.hackathon.users.entity;

import jakarta.persistence.*;
import lombok.*;
import mutsa.hackathon.global.Gender;
import mutsa.hackathon.match.entity.Match;

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

    // 성별 enum 정의 (내부 or 외부 클래스)
    public enum Gender {
        MALE, FEMALE
    }
}
