package mutsa.hackathon.dto.response;

import lombok.Builder;
import lombok.Getter;
import mutsa.hackathon.entity.User;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
public class UserResponse {

    private Long userId;
    private String name;
    private String email;
    private String destination;
    private String gender;
    private LocalDate birth;
    private BigDecimal rate;
    private int coin;
    private Long tokenId;

    public static UserResponse from(User user) {
        return UserResponse.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .destination(user.getDestination())
                .gender(user.getGender().name())
                .birth(user.getBirth())
                .rate(user.getRate())
                .coin(user.getCoin())
                .build();
    }
}
