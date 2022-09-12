package source.third_party.user_service.service;

import source.dto.request.UserSignupRequestDto;
import source.dto.response.BaseResponse;

public interface UserServiceThirdParty {

    public BaseResponse createUser(UserSignupRequestDto request) throws Exception;
}