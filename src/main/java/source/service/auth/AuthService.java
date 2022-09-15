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
}
