package source.third_party.user.service;

import source.dto.response.BaseResponse;
import source.third_party.user.dto.request.UserGetByIdRequestDto;

public interface UserServiceThirdParty {

    BaseResponse getUserById(UserGetByIdRequestDto userGetByIdRequestDto) throws Exception;
}