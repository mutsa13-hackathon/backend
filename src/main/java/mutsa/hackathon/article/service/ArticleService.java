package mutsa.hackathon.article.service;

import lombok.RequiredArgsConstructor;
import mutsa.hackathon.article.dto.request.ArticleRequestDTO;
import mutsa.hackathon.article.dto.response.ArticleResponseDTO;
import mutsa.hackathon.article.entity.Article;
import mutsa.hackathon.article.repository.ArticleRepository;
import mutsa.hackathon.users.service.UsersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UsersService usersService;

    // 게시글 등록
    public Long createArticle(ArticleRequestDTO requestDTO, String email) {
        var user = usersService.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        var article = Article.builder()
                .title(requestDTO.getTitle())
                .content(requestDTO.getContent())
                .user(user)
                .build();

        return articleRepository.save(article).getArticleId();
    }

    // 게시글 전체 목록 조회
    public List<ArticleResponseDTO> getAllArticles() {
        return articleRepository.findAll().stream()
                .map(ArticleResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // 게시글 단건 조회
    public ArticleResponseDTO getArticleById(Long id) {
        var article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        return ArticleResponseDTO.fromEntity(article);
    }

    // 게시글 수정
    @Transactional
    public void updateArticle(Long articleId, ArticleRequestDTO requestDTO, String email) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        if (!article.getUser().getEmail().equals(email)) {
            throw new SecurityException("본인이 작성한 게시글만 수정할 수 있습니다.");
        }

        article.setTitle(requestDTO.getTitle());
        article.setContent(requestDTO.getContent());
        // 필요 시 updatedAt 필드 갱신
    }

    // 게시글 삭제
    @Transactional
    public void deleteArticle(Long articleId, String email) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        if (!article.getUser().getEmail().equals(email)) {
            throw new SecurityException("본인이 작성한 게시글만 삭제할 수 있습니다.");
        }

        articleRepository.delete(article);
    }


}
