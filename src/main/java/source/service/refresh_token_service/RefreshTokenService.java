package source.service.refresh_token_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.dto.request.TokenRefreshRequestDto;
import source.dto.response.BaseResponse;
import source.entity.RefreshToken;
import source.entity.User;
import source.repository.RefreshTokenRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public interface RefreshTokenService {

    public Optional<RefreshToken> findByToken(String token);

    public RefreshToken createRefreshToken(User user);

    public RefreshToken verifyExpiration(RefreshToken token);

    public void deleteByUserId(String userId);

    public BaseResponse refreshToken(TokenRefreshRequestDto request);
}
