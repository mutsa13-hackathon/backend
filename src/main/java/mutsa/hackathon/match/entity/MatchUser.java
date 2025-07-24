package mutsa.hackathon.match.entity;
import jakarta.persistence.*;
import lombok.*;
import mutsa.hackathon.users.entity.Users;

@Entity
@Table(name = "match_user")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MatchUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long muserId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "matchId")
    private Match match;
}
