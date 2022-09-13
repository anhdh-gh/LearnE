package source.service;


import source.dto.request.UserComparePasswordRequestDto;
import source.dto.request.UserCreateRequestDto;
import source.dto.request.UserGetRoleByUserIdRequestDto;
import source.dto.response.BaseResponse;

public interface UserService {

    public BaseResponse createUser(UserCreateRequestDto request) throws Exception;
    
    public BaseResponse comparePassword(UserComparePasswordRequestDto userComparePasswordRequestDto);

    public BaseResponse getRoleByUserId(UserGetRoleByUserIdRequestDto userGetRoleByUserIdRequestDto) throws Exception;
}
