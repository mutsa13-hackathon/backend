package mutsa.hackathon.oauth.service;

import jakarta.transaction.Transactional;
import mutsa.hackathon.oauth.entity.RefreshToken;
import mutsa.hackathon.oauth.repository.RefreshTokenRepository;
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
