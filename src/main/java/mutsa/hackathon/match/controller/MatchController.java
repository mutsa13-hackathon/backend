package mutsa.hackathon.match.controller;


import lombok.RequiredArgsConstructor;
import mutsa.hackathon.match.dto.MatchRequestDto;
import mutsa.hackathon.match.dto.MatchResponseDto;
import mutsa.hackathon.match.entity.Matching;
import mutsa.hackathon.match.service.MatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/match")
public class MatchController {
    private final MatchService matchService;

    @PostMapping
    public ResponseEntity<List<MatchResponseDto>> findMatches(
            @RequestBody MatchRequestDto request,
            @RequestParam Long requesterId)
    {
        List<Matching> matches = matchService.findMatches(request, requesterId);
        List<MatchResponseDto> response = matches.stream()
                .map(MatchResponseDto::fromEntity)
                .toList();
        return ResponseEntity.ok(response);
    }
}
