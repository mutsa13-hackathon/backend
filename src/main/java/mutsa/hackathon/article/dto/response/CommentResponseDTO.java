package mutsa.hackathon.article.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentResponseDTO {
    private Long commentId;
    private String comment;
    private String userNickname; // user에서 가져온 nickname
    private Long articleId;
}
