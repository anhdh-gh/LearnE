package source.third_party.user_service.service;

import source.dto.response.BaseResponse;
import source.third_party.user_service.dto.request.UserGetByIdThirdPartyRequestDto;
import source.third_party.user_service.dto.request.UserSignUpThirdPartyRequestDto;

public interface UserServiceThirdParty {

    public BaseResponse createUser(UserSignUpThirdPartyRequestDto request) throws Exception;

    public BaseResponse getUserById(UserGetByIdThirdPartyRequestDto request) throws Exception;
}