package source.service.user_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.dto.request.AuthSignInRequestDto;
import source.dto.request.AuthSignUpRequestDto;
import source.dto.response.BaseResponse;
import source.third_party.user_service.service.AuthServiceThirdParty;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthServiceThirdParty userServiceThirdParty;

    @Override
    public BaseResponse signIn(AuthSignInRequestDto request) throws Exception {
        return userServiceThirdParty.signIn(request);
    }

    @Override
    public BaseResponse signUp(AuthSignUpRequestDto request) throws Exception {
        return userServiceThirdParty.signUp(request);
    }
}
