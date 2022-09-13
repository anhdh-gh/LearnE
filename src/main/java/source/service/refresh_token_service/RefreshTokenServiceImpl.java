package source.service.refresh_token_service;

import com.google.api.client.auth.oauth2.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import source.constant.JwtTokenTypeConstant;
import source.dto.request.TokenRefreshRequestDto;
import source.dto.response.BaseResponse;
import source.dto.response.TokenResponseDto;
import source.entity.RefreshToken;
import source.exception.BusinessErrors;
import source.exception.BusinessException;
import source.repository.RefreshTokenRepository;
import source.util.JwtUtil;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Value("${learne.app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findAll().stream()
            .filter(t -> t.getToken().equals(token))
            .findFirst();
    }

    @Override
    public RefreshToken createRefreshToken(String userId) {
        RefreshToken refreshToken = RefreshToken.builder()
            .token(jwtUtil.generateTokenFromUserId(userId))
            .expiryDate(new Date(new Date().getTime() + refreshTokenDurationMs))
            .userId(userId)
            .build();

        refreshToken = refreshTokenRepository.set(refreshToken);
        return refreshToken;
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(new Date()) < 0) {
            refreshTokenRepository.remove(token.getUserId());
            throw new BusinessException(BusinessErrors.FORBIDDEN_ERROR, "Refresh token was expired. Please make a new signin request");
        }

        return token;
    }

    @Override
    public void deleteByUserId(String userId) {
        refreshTokenRepository.remove(userId);
    }

    @Override
    public BaseResponse refreshToken(TokenRefreshRequestDto request) {
        String requestRefreshToken = request.getRefreshToken();
        return this.findByToken(requestRefreshToken)
            .map(this::verifyExpiration)
            .map(RefreshToken::getUserId)
            .map(userId -> {
                String token = jwtUtil.generateTokenFromUserId(userId);
                return BaseResponse.ofSucceeded(request.getRequestId(),
                    TokenResponseDto.builder().accessToken(token).refreshToken(requestRefreshToken).tokenType(JwtTokenTypeConstant.BEARER).build());
            })
            .orElseThrow(() -> new BusinessException(BusinessErrors.FORBIDDEN_ERROR,
                "Refresh token is not in database!")
            );
    }
}
