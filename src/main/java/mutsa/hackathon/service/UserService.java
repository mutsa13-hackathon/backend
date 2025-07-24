package mutsa.hackathon.service;

import lombok.RequiredArgsConstructor;
import mutsa.hackathon.dto.request.UserRequest;
import mutsa.hackathon.entity.RefreshToken;
import mutsa.hackathon.entity.User;
import mutsa.hackathon.entity.User.Gender;
import mutsa.hackathon.repository.RefreshTokenRepository;
import mutsa.hackathon.repository.UserRepository;
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
        User user = User.builder()
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
