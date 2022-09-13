package source.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.dto.request.UserGetAllRequestDto;
import source.dto.request.UserSignInRequestDto;
import source.dto.request.UserSignUpRequestDto;
import source.dto.response.BaseResponse;
import source.third_party.auth.service.AuthServiceThirdParty;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthServiceThirdParty authServiceThirdParty;

    @Override
    public BaseResponse signIn(UserSignInRequestDto request) throws Exception {
        return authServiceThirdParty.signIn(request);
    }

    @Override
    public BaseResponse signUp(UserSignUpRequestDto request) throws Exception {
        return authServiceThirdParty.signUp(request);
    }

    @Override
    public BaseResponse getAllUser(UserGetAllRequestDto request) throws Exception {
        return authServiceThirdParty.getAllUser(request);
    }
}
