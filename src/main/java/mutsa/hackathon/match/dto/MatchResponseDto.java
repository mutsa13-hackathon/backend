package mutsa.hackathon.match.dto;

import lombok.*;
import mutsa.hackathon.match.entity.Matching;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchResponseDto {
    private Long matchId;
    private String departure;
    private LocalDateTime createdAt;
    private String driver;
    private String carNumber;
    private Integer cost;
    private Double distance;
    private Long userId;
    private String userName;

    public static MatchResponseDto fromEntity(Matching match) {
        return MatchResponseDto.builder()
                .matchId(match.getMatchId())
                .departure(match.getDeparture())
                .createdAt(match.getCreatedAt())
                .driver(match.getDriver())
                .carNumber(match.getCarNumber())
                .cost(match.getCost())
                .distance(match.getDistance())
                .userId(match.getUser().getUserId())
                .userName(match.getUser().getName())
                .build();
    }
}
