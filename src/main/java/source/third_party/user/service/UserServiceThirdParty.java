package source.third_party.user.service;

import source.dto.response.BaseResponse;
import source.third_party.user.dto.request.UserGetByIdRequestDto;
import source.third_party.user.dto.request.UserGetListByIdsRequestDto;

public interface UserServiceThirdParty {

    BaseResponse getUserById(UserGetByIdRequestDto userGetByIdRequestDto) throws Exception;

    BaseResponse getUserByUserIds(UserGetListByIdsRequestDto request) throws Exception;
}