package mutsa.hackathon.match.dto;

import mutsa.hackathon.global.AgeGroup;
import mutsa.hackathon.global.Gender;

import java.time.LocalDateTime;

public class MatchRequest {

    private Gender gender;
    private AgeGroup age;
    private String destination;
    private LocalDateTime createdAt;
    private Integer withNum;
    private String departure;

}
