package source.service.auth;

import source.dto.request.*;
import source.dto.response.BaseResponse;

public interface AuthService {
    public BaseResponse signIn(UserSignInRequestDto request) throws Exception;

    public BaseResponse signUp(UserSignUpRequestDto request) throws Exception;

    public BaseResponse getAllUser(UserGetAllRequestDto request) throws Exception;


    public BaseResponse updateUser(UserUpdateRequestDto request) throws Exception;

    public BaseResponse deleteUser(UserDeleteRequestDto request) throws Exception;

    public BaseResponse refreshToken(UserRefreshToken request) throws Exception;

    public BaseResponse getUserInformation(UserGetUserInformationRequestDto request) throws Exception;

    public BaseResponse getUserById(UserGetUserByIdRequestDto request) throws Exception;

    public BaseResponse deleteUserById(UserDeleteByIdRequestDto request) throws Exception;

    public BaseResponse getQuestionByQuestionId(GetQuestionByQuestionIdRequestDto request) throws Exception;

    public BaseResponse createQuestion(CreateQuestionRequestDto request) throws Exception;

    public BaseResponse getAllQuestion(QuestionGetAllRequestDto request) throws Exception;

    public BaseResponse createCourse(CreateCourseRequestDto createCourseRequestDto) throws Exception;
}
