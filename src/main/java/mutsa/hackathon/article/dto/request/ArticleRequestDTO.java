package mutsa.hackathon.article.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleRequestDTO {
    private String title;
    private String content;
}