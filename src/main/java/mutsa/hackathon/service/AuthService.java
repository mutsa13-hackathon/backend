package mutsa.hackathon.service;

import jakarta.transaction.Transactional;
import mutsa.hackathon.entity.RefreshToken;
import mutsa.hackathon.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public void replaceRefreshToken(String email, String newToken) {
        refreshTokenRepository.deleteByEmail(email);
        refreshTokenRepository.flush(); // 중복 에러 방지
        refreshTokenRepository.save(
                RefreshToken.builder()
                        .email(email)
                        .token(newToken)
                        .build()
        );
    }

    @Transactional
    public void deleteRefreshToken(String email) {
        refreshTokenRepository.deleteByEmail(email);
    }
}
