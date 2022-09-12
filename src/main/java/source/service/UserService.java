package source.service;


import source.dto.request.UserCreateRequestDto;
import source.dto.response.BaseResponse;

public interface UserService {

    public BaseResponse<Void> createUser(UserCreateRequestDto request) throws Exception;
}
