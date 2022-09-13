package source.service;


import source.dto.request.*;
import source.dto.response.BaseResponse;

public interface UserService {

    public BaseResponse createUser(UserCreateRequestDto request) throws Exception;
    
    public BaseResponse comparePassword(UserComparePasswordRequestDto userComparePasswordRequestDto) throws Exception;

    public BaseResponse getUserById(UserGetByIdRequestDto userGetByIdRequestDto) throws Exception;

    public BaseResponse getAllUser(UserGetAllRequestDto userGetAllRequestDto) throws Exception;

    public BaseResponse updateUser(UserUpdateRequestDto userGetAllRequestDto) throws Exception;

    public BaseResponse deleteUser(UserDeleteRequestDto userDeleteRequestDto) throws Exception;
}
