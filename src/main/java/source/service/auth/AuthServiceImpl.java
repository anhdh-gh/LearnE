package source.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.dto.request.*;
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

    @Override
    public BaseResponse updateUser(UserUpdateRequestDto request) throws Exception {
        return authServiceThirdParty.updateUser(request);
    }

    @Override
    public BaseResponse deleteUser(UserDeleteRequestDto request) throws Exception {
        return authServiceThirdParty.deleteUser(request);
    }

    @Override
    public BaseResponse refreshToken(UserRefreshToken request) throws Exception {
        return authServiceThirdParty.refreshToken(request);
    }

    @Override
    public BaseResponse getUserInformation(UserGetUserInformationRequestDto request) throws Exception {
        return authServiceThirdParty.getUserInformation(request);
    }

    @Override
    public BaseResponse getUserById(UserGetUserByIdRequestDto request) throws Exception {
        return authServiceThirdParty.getUserById(request);
    }

    @Override
    public BaseResponse deleteUserById(UserDeleteByIdRequestDto request) throws Exception {
        return authServiceThirdParty.deleteUserById(request);
    }

    @Override
    public BaseResponse getQuestionByQuestionId(GetQuestionByQuestionIdRequestDto request) throws Exception {
        return authServiceThirdParty.getQuestionByQuestionId(request);
    }

    @Override
    public BaseResponse createQuestion(CreateQuestionRequestDto request) throws Exception {
        return authServiceThirdParty.createQuestion(request);
    }

    @Override
    public BaseResponse getAllQuestion(QuestionGetAllRequestDto request) throws Exception {
        return authServiceThirdParty.getAllQuestion(request);
    }

    @Override
    public BaseResponse createCourse(CreateCourseRequestDto request) throws Exception {
        return authServiceThirdParty.createCourse(request);
    }
}
