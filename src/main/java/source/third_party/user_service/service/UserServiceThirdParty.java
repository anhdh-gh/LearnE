package source.third_party.user_service.service;

import org.springframework.stereotype.Service;
import source.dto.request.UserSignInRequestDto;
import source.dto.request.UserSignUpRequestDto;
import source.dto.response.BaseResponse;


public interface UserServiceThirdParty {
    public BaseResponse signIn(UserSignInRequestDto request) throws Exception;

    public BaseResponse signUp(UserSignUpRequestDto request) throws Exception;
}
