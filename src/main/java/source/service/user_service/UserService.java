package source.service.user_service;

import source.dto.request.UserSignupRequestDto;
import source.dto.response.BaseResponse;

public interface UserService {

    public BaseResponse signUp(UserSignupRequestDto userRequestSignupDto) throws Exception;
}
