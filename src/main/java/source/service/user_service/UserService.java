package source.service.user_service;

import source.dto.request.UserSignUpRequestDto;
import source.dto.response.BaseResponse;

public interface UserService {

    public BaseResponse signUp(UserSignUpRequestDto userRequestSignupDto) throws Exception;
}
