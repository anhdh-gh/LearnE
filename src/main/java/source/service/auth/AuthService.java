package source.service.auth;

import source.dto.request.*;
import source.dto.request.studyset.*;
import source.dto.response.BaseResponse;

public interface AuthService {
    BaseResponse signIn(UserSignInRequestDto request) throws Exception;

    BaseResponse signUp(UserSignUpRequestDto request) throws Exception;

    BaseResponse getAllUser(UserGetAllRequestDto request) throws Exception;


    BaseResponse updateUser(UserUpdateRequestDto request) throws Exception;

    BaseResponse deleteUser(UserDeleteRequestDto request) throws Exception;

    BaseResponse refreshToken(UserRefreshToken request) throws Exception;

    BaseResponse getUserInformation(UserGetUserInformationRequestDto request) throws Exception;

    BaseResponse getUserById(UserGetUserByIdRequestDto request) throws Exception;

    BaseResponse deleteUserById(UserDeleteByIdRequestDto request) throws Exception;

    BaseResponse getQuestionByQuestionId(GetQuestionByQuestionIdRequestDto request) throws Exception;

    BaseResponse createQuestion(CreateQuestionRequestDto request) throws Exception;

    BaseResponse getAllQuestion(QuestionGetAllRequestDto request) throws Exception;

    BaseResponse getQuestionsByIds(QuestionGetByIdsRequestDto requestDto) throws Exception;

    BaseResponse getCourseDetailForUserRequestDto(GetCourseDetailForUserRequestDto request) throws Exception;

    BaseResponse updateLessonStatus(UpdateLessonStatusRequestDto request) throws Exception;

    BaseResponse createStudyset(StudysetDto request) throws Exception;

    BaseResponse updateStudyset(StudysetDto request) throws Exception;

    BaseResponse deleteStudyset(DeleteStudysetByIdRequestDto request) throws Exception;

    BaseResponse getStudysetById(GetStudysetByIdRequestDto request) throws Exception;

    BaseResponse saveTestResult(TestResultDto request) throws Exception;

    BaseResponse getAllStudysetByOwnerUserId(GetAllStudysetByOwnerUserIdRequestDto request) throws Exception;

    BaseResponse getAllStudyset(GetAllStudysetRequestDto request) throws Exception;

    BaseResponse getRankStudyset(GetRankStudysetRequestDto request) throws Exception;
}
