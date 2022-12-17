package source.third_party.auth.service;

import source.dto.request.*;
import source.dto.request.course.CreateCourseRequestDto;
import source.dto.request.course.DeleteCourseByIdRequestDto;
import source.dto.request.course.SearchCourseRequestDto;
import source.dto.request.course.UpdateCourseRequestDto;
import source.dto.request.questionBank.*;
import source.dto.request.studyset.TestResultDto;
import source.dto.request.studyset.*;
import source.dto.request.user.SearchUserRequestDto;
import source.dto.response.BaseResponse;


public interface AuthServiceThirdParty {
    BaseResponse signIn(UserSignInRequestDto request) throws Exception;

    BaseResponse signUp(UserSignUpRequestDto request) throws Exception;

    BaseResponse getAllUser(UserGetAllRequestDto request) throws Exception;

    BaseResponse updateUser(UserUpdateRequestDto request) throws Exception;

    BaseResponse deleteUser(UserDeleteRequestDto request) throws Exception;

    BaseResponse refreshToken(UserRefreshToken request) throws Exception;

    BaseResponse getUserInformation(UserGetUserInformationRequestDto request) throws Exception;

    BaseResponse getUserById(UserGetUserByIdRequestDto request) throws Exception;

    BaseResponse deleteUserById(UserDeleteByIdRequestDto request) throws Exception;

    BaseResponse getCourseDetailForUserRequestDto(GetCourseDetailForUserRequestDto request) throws Exception;

    BaseResponse getCourseById(GetCourseByIdRequestDto request) throws Exception;

    BaseResponse createCourse(CreateCourseRequestDto request) throws Exception;

    BaseResponse deleteCourse(DeleteCourseByIdRequestDto request) throws Exception;

    BaseResponse updateCourse(UpdateCourseRequestDto request) throws Exception;

    BaseResponse getAllCourse(GetAllCourseRequestDto request) throws Exception;

    BaseResponse updateLessonStatus(UpdateLessonStatusRequestDto request) throws Exception;

    BaseResponse createStudyset(StudysetDto request) throws Exception;

    BaseResponse updateStudyset(StudysetDto request) throws Exception;

    BaseResponse deleteStudyset(DeleteStudysetByIdRequestDto request) throws Exception;

    BaseResponse getStudysetById(GetStudysetByIdRequestDto request) throws Exception;

    BaseResponse saveTestResultStudyset(TestResultDto request) throws Exception;

    BaseResponse getAllStudysetByOwnerUserId(GetAllStudysetByOwnerUserIdRequestDto request) throws Exception;

    BaseResponse getAllStudyset(GetAllStudysetRequestDto request) throws Exception;

    BaseResponse searchAllStudysetByOwnerUserId(SearchAllStudysetByOwnerUserIdRequestDto request) throws Exception;

    BaseResponse searchAllStudyset(SearchAllStudysetRequestDto request) throws Exception;

    BaseResponse getRankStudyset(GetRankStudysetRequestDto request) throws Exception;

    BaseResponse checkOwnerStudysetValid(CheckOwnerStudysetValidRequestDto request) throws Exception;

    BaseResponse createQuestion(QuestionDto request) throws Exception;

    BaseResponse updateQuestion(QuestionDto request) throws Exception;

    BaseResponse deleteQuestionById(DeleteQuestionByIdRequestDto request) throws Exception;

    BaseResponse getQuestionById(GetQuestionByIdRequestDto request) throws Exception;

    BaseResponse getAllQuestion(GetAllQuestionDto request) throws Exception;

    BaseResponse getRankQuestion(GetRankQuestionDto request) throws Exception;

    BaseResponse saveTestResultQuestion(source.dto.request.questionBank.TestResultDto request) throws Exception;

    BaseResponse searchQuestion(SearchQuestionRequestDto request) throws Exception;

    BaseResponse searchCourse(SearchCourseRequestDto request) throws Exception;

    BaseResponse searchUser(SearchUserRequestDto request) throws Exception;
}
