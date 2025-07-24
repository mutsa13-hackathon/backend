package mutsa.hackathon.article.controller;

import lombok.RequiredArgsConstructor;
import mutsa.hackathon.article.dto.request.CommentRequestDTO;
import mutsa.hackathon.article.dto.response.CommentResponseDTO;
import mutsa.hackathon.article.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/article/comment")
public class CommentController {

    private final CommentService commentService;

    // 댓글 생성
    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody CommentRequestDTO requestDTO) {
        Long id = commentService.createComment(requestDTO);
        return ResponseEntity.ok("댓글이 등록되었습니다. ID: " + id);
    }

    // 댓글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("댓글이 삭제되었습니다.");
    }

    // 댓글 목록 조회 (게시글 기준)
    @GetMapping
    public ResponseEntity<List<CommentResponseDTO>> getCommentsByArticleId(@RequestParam Long articleId) {
        List<CommentResponseDTO> comments = commentService.getCommentsByArticleId(articleId);
        return ResponseEntity.ok(comments);
    }

    // 댓글 수정
    @PutMapping("/{id}")
    public ResponseEntity<String> updateComment(
            @PathVariable Long id,
            @RequestBody CommentRequestDTO requestDTO
    ) {
        commentService.updateComment(id, requestDTO);
        return ResponseEntity.ok("댓글이 수정되었습니다.");
    }
}
