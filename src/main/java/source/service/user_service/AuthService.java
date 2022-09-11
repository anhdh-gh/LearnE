package source.service.user_service;

import source.dto.request.AuthSignInRequestDto;
import source.dto.request.AuthSignUpRequestDto;
import source.dto.response.BaseResponse;

public interface AuthService {
    public BaseResponse signIn(AuthSignInRequestDto request) throws Exception;

    public BaseResponse signUp(AuthSignUpRequestDto request) throws Exception;
}
