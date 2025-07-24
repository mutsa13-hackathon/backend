package mutsa.hackathon.match.entity;
import jakarta.persistence.*;
import lombok.*;
import mutsa.hackathon.global.AgeGroup;
import mutsa.hackathon.global.Gender;
import mutsa.hackathon.users.entity.Users;

import java.time.LocalDateTime;

@Entity
@Table(name = "match_request")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MatchRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private AgeGroup age;

    private String destination;
    private String departure;
    private Integer withNum;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Users user;
}