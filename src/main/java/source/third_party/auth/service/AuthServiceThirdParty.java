package source.third_party.auth.service;

import source.dto.request.*;
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

    BaseResponse getQuestionByQuestionId(GetQuestionByQuestionIdRequestDto request) throws Exception;

    BaseResponse createQuestion(CreateQuestionRequestDto request) throws Exception;

    BaseResponse getAllQuestion(QuestionGetAllRequestDto request) throws Exception;

    BaseResponse getQuestionByIds(QuestionGetByIdsRequestDto requestDto) throws Exception;

    BaseResponse getCourseDetailForUserRequestDto(GetCourseDetailForUserRequestDto request) throws Exception;

    BaseResponse updateLessonStatus(UpdateLessonStatusRequestDto request) throws Exception;
}
