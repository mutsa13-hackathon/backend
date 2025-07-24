package mutsa.hackathon.article.entity;
import jakarta.persistence.*;
import lombok.*;
import mutsa.hackathon.users.entity.Users;

@Entity
@Table(name = "article")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;

    private String title;

    @Lob
    private String content;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Users user;
}
