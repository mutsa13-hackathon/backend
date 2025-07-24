package mutsa.hackathon.article.repository;

import mutsa.hackathon.article.entity.Article;
import mutsa.hackathon.article.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByArticle(Article article);
}
