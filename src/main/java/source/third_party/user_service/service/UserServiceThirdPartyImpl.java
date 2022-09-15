package source.third_party.user_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import source.constant.RequestKeyConstant;
import source.dto.request.*;
import source.dto.response.BaseResponse;
import source.third_party.user_service.constant.RouterUserServiceConstant;
import source.third_party.user_service.dto.request.UserGetByIdThirdPartyRequestDto;
import source.third_party.user_service.dto.request.UserSignUpThirdPartyRequestDto;
import source.util.JsonUtil;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

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

    private HttpEntity<BasicRequest> getHeader(BasicRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set(RequestKeyConstant.X_REQUEST_ID, request.getRequestId());
        headers.set(RequestKeyConstant.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.ACCEPT_CHARSET, StandardCharsets.UTF_8.name());
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(request, headers);
    }
}