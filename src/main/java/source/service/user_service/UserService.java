package source.service.user_service;

import org.springframework.stereotype.Service;
import source.dto.request.UserSignupDto;
import source.dto.response.BaseResponse;

public interface UserService {

    public BaseResponse signUp(UserSignupDto userSignupDto) throws Exception;
}
