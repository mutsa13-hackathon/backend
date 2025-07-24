package mutsa.hackathon.article.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ArticleResponseDTO {
    private Long articleId;
    private String title;
    private String content;
    private String writerName;  // Users.name

    public static ArticleResponseDTO fromEntity(mutsa.hackathon.article.entity.Article article) {
        return ArticleResponseDTO.builder()
                .articleId(article.getArticleId())
                .title(article.getTitle())
                .content(article.getContent())
                .writerName(article.getUser().getName())
                .build();
    }
}