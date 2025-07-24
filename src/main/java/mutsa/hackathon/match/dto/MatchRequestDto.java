package mutsa.hackathon.match.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mutsa.hackathon.global.AgeGroup;
import mutsa.hackathon.global.Gender;
import mutsa.hackathon.users.entity.Users;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class MatchRequestDto {
    private Gender gender;
    private AgeGroup age;
    private String destination;
    private LocalDateTime createdAt;
    private Integer withNum;
    private String departure;

}
