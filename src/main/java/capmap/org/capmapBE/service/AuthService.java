package capmap.org.capmapBE.service;

import capmap.org.capmapBE.config.jwt.TokenProvider;
import capmap.org.capmapBE.domain.User;
import capmap.org.capmapBE.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserService userService; // 사용자 서비스
    private final TokenProvider tokenProvider; // TokenProvider
    private final RefreshTokenService refreshTokenService; // RefreshTokenService

    public LoginResponse login(String email, String password) {
        // 사용자 인증
        User user = userService.authenticate(email, password);

        // JWT 토큰 생성
        String accessToken = tokenProvider.generateToken(user, Duration.ofHours(2)); // 2시간 유효
        String refreshToken = tokenProvider.generateToken(user, Duration.ofDays(30)); // 30일 유효

        // 리프레시 토큰 저장
        refreshTokenService.saveRefreshToken(user.getId(), refreshToken);

        return new LoginResponse(accessToken, refreshToken);
    }
}