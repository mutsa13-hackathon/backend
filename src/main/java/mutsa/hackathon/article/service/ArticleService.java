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

    // 게시글 저장
    public Long createArticle(ArticleRequestDTO requestDTO) {
        var user = usersService.getUserById(requestDTO.getUserId());

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

    // 게시글 삭제
    @Transactional
    public void deleteArticle(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        articleRepository.delete(article);
    }

    // 게시글 수정
    @Transactional
    public void updateArticle(Long articleId, ArticleRequestDTO requestDTO) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        article.setTitle(requestDTO.getTitle());
        article.setContent(requestDTO.getContent());
        // 필요 시 updatedAt 필드 갱신도 여기에 추가
    }


}
