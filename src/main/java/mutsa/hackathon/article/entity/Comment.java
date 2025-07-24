package mutsa.hackathon.article.entity;

import jakarta.persistence.*;
import lombok.*;
import mutsa.hackathon.users.entity.Users;

@Entity
@Table(name = "comment")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "articleId")
    private Article article;
}
