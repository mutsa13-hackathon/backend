package mutsa.hackathon.match.entity;

import jakarta.persistence.*;
import lombok.*;
import mutsa.hackathon.users.entity.Users;

@Entity
@Table(name = "'match'")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchId;

    private Integer cost;

    private Double distance;

    private String driver;

    private String carNumber;

    private String departure;

    @OneToOne
    @JoinColumn(name = "userId", unique = true)  // 유일한 사용자
    private Users user;
}