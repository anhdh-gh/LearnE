package source.third_party.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import source.dto.request.*;
import source.dto.response.BaseResponse;
import source.third_party.auth.constant.RouterAuthServiceConstant;
import source.util.JsonUtil;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

@Service
public class AuthServiceThirdPartyImpl implements AuthServiceThirdParty {
    @Value("${service.auth.baseurl}")
    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public BaseResponse signIn(UserSignInRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.SIGN_IN),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {});

        return JsonUtil.getGenericObject(responseEntity.getBody(),BaseResponse.class);
    }

    @Override
    public BaseResponse signUp(UserSignUpRequestDto request) throws Exception{
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.SIGN_UP),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {});

        return JsonUtil.getGenericObject(responseEntity.getBody(),BaseResponse.class);
    }

    @Override
    public BaseResponse getAllUser(UserGetAllRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.GET_ALL_USER),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {});

        return JsonUtil.getGenericObject(responseEntity.getBody(),BaseResponse.class);
    }

    @Override
    public BaseResponse updateUser(UserUpdateRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.UPDATE_USER),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {});

        return JsonUtil.getGenericObject(responseEntity.getBody(),BaseResponse.class);
    }

    @Override
    public BaseResponse deleteUser(UserDeleteRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.DELETE_USER),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {});

        return JsonUtil.getGenericObject(responseEntity.getBody(),BaseResponse.class);
    }

    @Override
    public BaseResponse refreshToken(UserRefreshToken request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.REFRESH_TOKEN),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {});

        return JsonUtil.getGenericObject(responseEntity.getBody(),BaseResponse.class);
    }

    @Override
    public BaseResponse getUserInformation(UserGetUserInformationRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.GET_USER_INFORMATION),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {});

        return JsonUtil.getGenericObject(responseEntity.getBody(),BaseResponse.class);
    }

    @Override
    public BaseResponse getUserById(UserGetUserByIdRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.GET_USER_BY_ID),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {});

        return JsonUtil.getGenericObject(responseEntity.getBody(),BaseResponse.class);
    }

    @Override
    public BaseResponse deleteUserById(UserDeleteByIdRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
                String.format("%s%s", baseUrl, RouterAuthServiceConstant.DELETE_USER_BY_ID),
                HttpMethod.POST,
                getHeader(request),
                new ParameterizedTypeReference<BaseResponse>() {});

        return JsonUtil.getGenericObject(responseEntity.getBody(),BaseResponse.class);
    }


    private HttpEntity<BasicRequest> getHeader(BasicRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-Request-ID", request.getRequestId());
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.ACCEPT_CHARSET, StandardCharsets.UTF_8.name());
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", request.getAuthorization());
        return new HttpEntity<>(request, headers);
    }
}
