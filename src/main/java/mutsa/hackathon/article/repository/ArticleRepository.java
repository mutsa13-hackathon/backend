package mutsa.hackathon.article.repository;

import mutsa.hackathon.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
