package mutsa.hackathon.users.entity;

import jakarta.persistence.*;
import lombok.*;
import mutsa.hackathon.global.Gender;
import mutsa.hackathon.match.entity.Match;
import mutsa.hackathon.oauth.entity.RefreshToken;

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

    private Double rate;

    private Integer coin;

    private String email;

    @OneToOne
    @JoinColumn(name = "tokenId")
    private RefreshToken refreshToken;

    @OneToOne(mappedBy = "user")
    private Match match;
}
