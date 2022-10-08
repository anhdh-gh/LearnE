package source.third_party.user_service.service;

import source.dto.request.user.UserDeleteRequestDto;
import source.dto.request.user.UserGetAllRequestDto;
import source.dto.request.user.UserGetInfoRequestDto;
import source.dto.request.user.UserUpdateRequestDto;
import source.dto.response.BaseResponse;
import source.third_party.user_service.dto.request.UserSignUpThirdPartyRequestDto;

public interface UserServiceThirdParty {

    public BaseResponse createUser(UserSignUpThirdPartyRequestDto request) throws Exception;

    public BaseResponse getAllUser(UserGetAllRequestDto request) throws Exception;

    public BaseResponse updateUser(UserUpdateRequestDto userUpdateRequestDto) throws Exception;

    public BaseResponse deleteUser(UserDeleteRequestDto userDeleteRequestDto) throws Exception;

    public BaseResponse getUserInfo(UserGetInfoRequestDto userGetInfoRequestDto) throws Exception;
}