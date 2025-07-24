package mutsa.hackathon.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

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
