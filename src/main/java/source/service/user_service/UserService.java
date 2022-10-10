package source.service.user_service;

import source.dto.request.*;
import source.dto.response.BaseResponse;

public interface UserService {

    public BaseResponse signUp(UserSignUpRequestDto userSignUpRequestDto) throws Exception;

    public BaseResponse signIn(UserSignInRequestDto userSignInRequestDto) throws Exception;

    public BaseResponse getAllUser(UserGetAllRequestDto request) throws Exception;

    public BaseResponse updateUser(UserUpdateRequestDto userUpdateRequestDto) throws Exception;

    public BaseResponse deleteUser(UserDeleteRequestDto userDeleteRequestDto) throws Exception;

    public BaseResponse getUserInfo(UserGetInfoRequestDto userGetInfoRequestDto) throws Exception;
}
