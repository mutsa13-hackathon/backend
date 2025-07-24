package mutsa.hackathon.article.controller;

import lombok.RequiredArgsConstructor;
import mutsa.hackathon.article.dto.request.ArticleRequestDTO;
import mutsa.hackathon.article.dto.response.ArticleResponseDTO;
import mutsa.hackathon.article.service.ArticleService;
import mutsa.hackathon.users.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/article")
public class ArticleController {

    private final ArticleService articleService;

    // 게시글 작성
    @PostMapping
    public ResponseEntity<?> createArticle(@RequestBody ArticleRequestDTO requestDTO,
                                           @AuthenticationPrincipal UserDTO userDTO) {
        Long id = articleService.createArticle(requestDTO, userDTO.getUsername());
        return ResponseEntity.ok("게시글이 등록되었습니다. ID: " + id);
    }

    // 게시글 목록 조회
    @GetMapping
    public ResponseEntity<List<ArticleResponseDTO>> getAllArticles() {
        return ResponseEntity.ok(articleService.getAllArticles());
    }

    // 게시글 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponseDTO> getArticle(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.getArticleById(id));
    }

    // 게시글 수정
    @PutMapping("/{id}")
    public ResponseEntity<?> updateArticle(
            @PathVariable("id") Long id,
            @RequestBody ArticleRequestDTO requestDTO,
            @AuthenticationPrincipal UserDTO userDTO
    ) {
        articleService.updateArticle(id, requestDTO, userDTO.getUsername());
        return ResponseEntity.ok("게시글이 수정되었습니다.");
    }
    
    // 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticle(
            @PathVariable("id") Long id,
            @AuthenticationPrincipal UserDTO userDTO
    ) {
        articleService.deleteArticle(id, userDTO.getUsername());
        return ResponseEntity.ok("게시글이 삭제되었습니다.");
    }


}
