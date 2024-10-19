package capmap.org.capmapBE.service;


import lombok.RequiredArgsConstructor;
import capmap.org.capmapBE.domain.RefreshToken;
import capmap.org.capmapBE.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public void saveRefreshToken(Long userId, String refreshToken) {
        // 리프레시 토큰 저장 로직 (기존 토큰이 있다면 업데이트)
        RefreshToken token = new RefreshToken(userId, refreshToken);
        refreshTokenRepository.save(token);
    }


    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected token"));
    }
}
