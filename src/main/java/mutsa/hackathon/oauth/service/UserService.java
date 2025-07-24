package mutsa.hackathon.oauth.service;

import lombok.RequiredArgsConstructor;
import mutsa.hackathon.users.dto.request.UserRequest;
import mutsa.hackathon.oauth.entity.RefreshToken;
import mutsa.hackathon.users.entity.Users;
import mutsa.hackathon.users.entity.Users.Gender;
import mutsa.hackathon.oauth.repository.RefreshTokenRepository;
import mutsa.hackathon.oauth.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    // 유저 생성
    public Long createUser(UserRequest request) {
        Users user = Users.builder()
                .name(request.getName())
                .email(request.getEmail())
                .gender(Gender.valueOf(request.getGender()))
                .birth(LocalDate.parse(request.getBirth()))
                .destination(request.getDestination())
                .rate(new BigDecimal(request.getRate()))
                .coin(request.getCoin())
                .build();

        return userRepository.save(user).getUserId();  // user.getId() → user.getUserId()
    }
}
