package source.service.user_service;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.dto.request.UserSignInRequestDto;
import source.dto.request.UserSignUpRequestDto;
import source.dto.response.BaseResponse;
import source.third_party.user_service.service.UserServiceThirdParty;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserServiceThirdParty userServiceThirdParty;

    @Override
    public BaseResponse signIn(UserSignInRequestDto request) throws Exception {
        return userServiceThirdParty.signIn(request);
    }

    @Override
    public BaseResponse signUp(UserSignUpRequestDto request) throws Exception {
        return userServiceThirdParty.signUp(request);
    }
}
