package mutsa.hackathon.oauth.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "refreshToken")
@Builder
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 500)
    private String token;

}