package source.third_party.user_service.service;

import source.dto.request.AuthSignInRequestDto;
import source.dto.request.AuthSignUpRequestDto;
import source.dto.response.BaseResponse;


public interface AuthServiceThirdParty {
    public BaseResponse signIn(AuthSignInRequestDto request) throws Exception;

    public BaseResponse signUp(AuthSignUpRequestDto request) throws Exception;
}
