package source.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.dto.request.*;
import source.dto.request.course.create_course.CreateCourseRequestDto;
import source.dto.request.studyset.*;
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
    public BaseResponse getQuestionsByIds(QuestionGetByIdsRequestDto requestDto) throws Exception {
        return authServiceThirdParty.getQuestionByIds(requestDto);
    }

    @Override
    public BaseResponse getCourseDetailForUserRequestDto(GetCourseDetailForUserRequestDto request) throws Exception {
        return authServiceThirdParty.getCourseDetailForUserRequestDto(request);
    }

    @Override
    public BaseResponse getAllCourse(GetAllCourseRequestDto request) throws Exception {
        return authServiceThirdParty.getAllCourse(request);
    }

    @Override
    public BaseResponse getCourseById(GetCourseByIdRequestDto request) throws Exception {
        return authServiceThirdParty.getCourseById(request);
    }

    @Override
    public BaseResponse createCourse(CreateCourseRequestDto request) throws Exception {
        return authServiceThirdParty.createCourse(request);
    }

    @Override
    public BaseResponse updateLessonStatus(UpdateLessonStatusRequestDto request) throws Exception {
        return authServiceThirdParty.updateLessonStatus(request);
    }

    @Override
    public BaseResponse createStudyset(StudysetDto request) throws Exception {
        return authServiceThirdParty.createStudyset(request);
    }

    @Override
    public BaseResponse updateStudyset(StudysetDto request) throws Exception {
        return authServiceThirdParty.updateStudyset(request);
    }

    @Override
    public BaseResponse deleteStudyset(DeleteStudysetByIdRequestDto request) throws Exception {
        return authServiceThirdParty.deleteStudyset(request);
    }

    @Override
    public BaseResponse getStudysetById(GetStudysetByIdRequestDto request) throws Exception {
        return authServiceThirdParty.getStudysetById(request);
    }

    @Override
    public BaseResponse saveTestResult(TestResultDto request) throws Exception {
        return authServiceThirdParty.saveTestResult(request);
    }

    @Override
    public BaseResponse getAllStudysetByOwnerUserId(GetAllStudysetByOwnerUserIdRequestDto request) throws Exception {
        return authServiceThirdParty.getAllStudysetByOwnerUserId(request);
    }

    @Override
    public BaseResponse getAllStudyset(GetAllStudysetRequestDto request) throws Exception {
        return authServiceThirdParty.getAllStudyset(request);
    }

    @Override
    public BaseResponse searchAllStudysetByOwnerUserId(SearchAllStudysetByOwnerUserIdRequestDto request) throws Exception {
        return authServiceThirdParty.searchAllStudysetByOwnerUserId(request);
    }

    @Override
    public BaseResponse searchAllStudyset(SearchAllStudysetRequestDto request) throws Exception {
        return authServiceThirdParty.searchAllStudyset(request);
    }

    @Override
    public BaseResponse getRankStudyset(GetRankStudysetRequestDto request) throws Exception {
        return authServiceThirdParty.getRankStudyset(request);
    }

    @Override
    public BaseResponse checkOwnerStudysetValid(CheckOwnerStudysetValidRequestDto request) throws Exception {
        return authServiceThirdParty.checkOwnerStudysetValid(request);
    }
}
