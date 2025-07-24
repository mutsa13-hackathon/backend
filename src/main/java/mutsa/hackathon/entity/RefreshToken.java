package mutsa.hackathon.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "refresh_tokens")
@Getter
@NoArgsConstructor
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;  // ← userId → email

    @Column(nullable = false, length = 500)
    private String token;

    @Builder
    public RefreshToken(String email, String token) {
        this.email = email;
        this.token = token;
    }
}
