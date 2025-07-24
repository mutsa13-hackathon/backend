package mutsa.hackathon.article.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDTO {
    private String comment;
    private Long userId;
    private Long articleId;
}
