package source.third_party.user.service;

import source.dto.request.UserDeleteRequestDto;
import source.dto.request.UserGetAllRequestDto;
import source.dto.request.UserGetInfoRequestDto;
import source.dto.request.UserUpdateRequestDto;
import source.dto.response.BaseResponse;
import source.third_party.user.dto.request.UserSignUpThirdPartyRequestDto;

public interface UserServiceThirdParty {

    BaseResponse createUser(UserSignUpThirdPartyRequestDto request) throws Exception;

    BaseResponse getAllUser(UserGetAllRequestDto request) throws Exception;

    BaseResponse updateUser(UserUpdateRequestDto userUpdateRequestDto) throws Exception;

    BaseResponse deleteUser(UserDeleteRequestDto userDeleteRequestDto) throws Exception;

    BaseResponse getUserInfo(UserGetInfoRequestDto userGetInfoRequestDto) throws Exception;
}