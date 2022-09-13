package source.service.user_service;

import source.dto.request.UserSignInRequestDto;
import source.dto.request.UserSignUpRequestDto;
import source.dto.response.BaseResponse;

public interface UserService {

    public BaseResponse signUp(UserSignUpRequestDto userSignUpRequestDto) throws Exception;

    public BaseResponse signIn(UserSignInRequestDto userSignInRequestDto) throws Exception;
}
