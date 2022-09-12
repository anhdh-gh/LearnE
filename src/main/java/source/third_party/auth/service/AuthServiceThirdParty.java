package source.third_party.auth.service;

import source.dto.request.UserSignInRequestDto;
import source.dto.request.UserSignUpRequestDto;
import source.dto.response.BaseResponse;


public interface AuthServiceThirdParty {
    public BaseResponse signIn(UserSignInRequestDto request) throws Exception;

    public BaseResponse signUp(UserSignUpRequestDto request) throws Exception;
}
