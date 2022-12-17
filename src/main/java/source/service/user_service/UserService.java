package source.service.user_service;

import source.dto.request.*;
import source.dto.request.user.SearchUserRequestDto;
import source.dto.response.BaseResponse;

public interface UserService {

    BaseResponse signUp(UserSignUpRequestDto userSignUpRequestDto) throws Exception;

    BaseResponse signIn(UserSignInRequestDto userSignInRequestDto) throws Exception;

    BaseResponse getAllUser(UserGetAllRequestDto request) throws Exception;

    BaseResponse updateUser(UserUpdateRequestDto userUpdateRequestDto) throws Exception;

    BaseResponse deleteUser(UserDeleteRequestDto userDeleteRequestDto) throws Exception;

    BaseResponse getUserInfo(UserGetInfoRequestDto userGetInfoRequestDto) throws Exception;

    BaseResponse searchUser(SearchUserRequestDto request) throws Exception;
}
