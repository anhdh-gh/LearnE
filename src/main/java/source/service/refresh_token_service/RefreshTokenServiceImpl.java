package source.service.refresh_token_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.constant.JwtTokenTypeConstant;
import source.dto.request.TokenRefreshRequestDto;
import source.dto.response.BaseResponse;
import source.dto.response.TokenResponseDto;
import source.entity.RefreshToken;
import source.entity.User;
import source.exception.BusinessErrors;
import source.exception.BusinessException;
import source.repository.RefreshTokenRepository;
import source.repository.UserRepository;
import source.util.JwtUtil;

import java.util.Date;
import java.util.Optional;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final Long refreshTokenDurationMs = 86400000L;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findAll().stream()
            .filter(t -> t.getToken().equals(token))
            .findFirst();
    }

    @Override
    public RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = RefreshToken.builder()
            .token(jwtUtil.generateJwtToken(user))
            .expiryDate(new Date(new Date().getTime() + refreshTokenDurationMs))
            .userId(user.getId())
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
                User user = userRepository.get(userId);
                String token = jwtUtil.generateJwtToken(user);
                return BaseResponse.ofSucceeded(request.getRequestId(),
                    TokenResponseDto.builder().user(user).accessToken(token).refreshToken(requestRefreshToken).tokenType(JwtTokenTypeConstant.BEARER).build());
            })
            .orElseThrow(() -> new BusinessException(BusinessErrors.FORBIDDEN_ERROR,
                "Refresh token is not in database!")
            );
    }
}
