package source.third_party.user_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import source.dto.request.user.UserDeleteRequestDto;
import source.dto.request.user.UserGetAllRequestDto;
import source.dto.request.user.UserGetInfoRequestDto;
import source.dto.request.user.UserUpdateRequestDto;
import source.dto.response.BaseResponse;
import source.third_party.user_service.constant.RouterUserServiceConstant;
import source.third_party.user_service.dto.request.UserSignUpThirdPartyRequestDto;
import source.util.JsonUtil;

import static source.util.HttpUtil.getHeader;

@Service
public class UserServiceThirdPartyImpl implements UserServiceThirdParty {

    @Value("${service.user.baseurl}")
    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public BaseResponse createUser(UserSignUpThirdPartyRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterUserServiceConstant.USER_CREATE),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {});

        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }
    
    @Override
    public BaseResponse getAllUser(UserGetAllRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterUserServiceConstant.GET_ALL_USER),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {});

        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse updateUser(UserUpdateRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
                String.format("%s%s", baseUrl, RouterUserServiceConstant.UPDATE_USER),
                HttpMethod.POST,
                getHeader(request),
                new ParameterizedTypeReference<BaseResponse>() {});

        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse deleteUser(UserDeleteRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
                String.format("%s%s", baseUrl, RouterUserServiceConstant.DELETE_USER),
                HttpMethod.POST,
                getHeader(request),
                new ParameterizedTypeReference<BaseResponse>() {});

        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse getUserInfo(UserGetInfoRequestDto userGetInfoRequestDto) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
                String.format("%s%s", baseUrl, RouterUserServiceConstant.GET_USER_BY_ID),
                HttpMethod.POST,
                getHeader(userGetInfoRequestDto),
                new ParameterizedTypeReference<BaseResponse>() {});
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }


}