package mutsa.hackathon.article.service;

import lombok.RequiredArgsConstructor;
import mutsa.hackathon.article.dto.request.CommentRequestDTO;
import mutsa.hackathon.article.dto.response.CommentResponseDTO;
import mutsa.hackathon.article.entity.Article;
import mutsa.hackathon.article.entity.Comment;
import mutsa.hackathon.article.repository.ArticleRepository;
import mutsa.hackathon.article.repository.CommentRepository;
import mutsa.hackathon.users.entity.Users;
import mutsa.hackathon.users.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UsersRepository userRepository;
    private final ArticleRepository articleRepository;

    // 댓글 생성
    public Long createComment(CommentRequestDTO requestDTO, String email) {
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        Article article = articleRepository.findById(requestDTO.getArticleId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        Comment comment = Comment.builder()
                .comment(requestDTO.getComment())
                .user(user)
                .article(article)
                .build();

        return commentRepository.save(comment).getCommentId();
    }

    // 댓글 삭제
    public void deleteComment(Long commentId, String email) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        if (!comment.getUser().getEmail().equals(email)) {
            throw new SecurityException("본인이 작성한 댓글만 삭제할 수 있습니다.");
        }

        commentRepository.delete(comment);
    }

    // 게시글 기준 댓글 목록 조회
    public List<CommentResponseDTO> getCommentsByArticleId(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        List<Comment> commentList = commentRepository.findByArticle(article);

        return commentList.stream()
                .map(comment -> new CommentResponseDTO(
                        comment.getCommentId(),
                        comment.getComment(),
                        comment.getUser().getName(),
                        comment.getArticle().getArticleId()
                ))
                .toList();
    }

    // 댓글 수정
    public void updateComment(Long commentId, CommentRequestDTO requestDTO, String email) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        if (!comment.getUser().getEmail().equals(email)) {
            throw new SecurityException("본인이 작성한 댓글만 수정할 수 있습니다.");
        }

        comment.setComment(requestDTO.getComment());
        commentRepository.save(comment);
    }
}
