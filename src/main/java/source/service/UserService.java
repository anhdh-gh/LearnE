package source.service;


import source.dto.request.*;
import source.dto.response.BaseResponse;

public interface UserService {

    BaseResponse createUser(UserCreateRequestDto request) throws Exception;

    BaseResponse comparePassword(UserComparePasswordRequestDto userComparePasswordRequestDto) throws Exception;

    BaseResponse getUserById(UserGetByIdRequestDto userGetByIdRequestDto) throws Exception;

    BaseResponse getAllUser(UserGetAllRequestDto userGetAllRequestDto) throws Exception;

    BaseResponse updateUser(UserUpdateRequestDto userGetAllRequestDto) throws Exception;

    BaseResponse deleteUser(UserDeleteRequestDto userDeleteRequestDto) throws Exception;

    BaseResponse getUserByUserIds(UserGetListByIdsRequestDto request) throws Exception;

    BaseResponse searchUser(SearchUserRequestDto request) throws Exception;
}
