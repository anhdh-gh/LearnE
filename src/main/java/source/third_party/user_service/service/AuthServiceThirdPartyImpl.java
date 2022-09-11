package source.third_party.user_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import source.dto.request.AuthSignInRequestDto;
import source.dto.request.AuthSignUpRequestDto;
import source.dto.response.BaseResponse;
import source.third_party.user_service.constant.RouterUserServiceConstant;
import source.util.CommonUtil;

import java.nio.charset.StandardCharsets;

@Service
public class AuthServiceThirdPartyImpl implements AuthServiceThirdParty {
    @Value("${service.auth.baseurl}")
    private String baseUrl;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public BaseResponse signIn(AuthSignInRequestDto request) throws Exception {
        HttpEntity<Object> httpEntity = new HttpEntity<>(request, prepareHeaders());
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
                String.format("%s%s", baseUrl, RouterUserServiceConstant.SIGN_IN),
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<BaseResponse>() {});

        BaseResponse baseResponse = CommonUtil.getGenericObject(responseEntity.getBody(),BaseResponse.class);
        return baseResponse;
    }

    @Override
    public BaseResponse signUp(AuthSignUpRequestDto request) throws Exception{
        HttpEntity<Object> httpEntity = new HttpEntity<>(request, prepareHeaders());
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
                String.format("%s%s", baseUrl, RouterUserServiceConstant.SIGN_UP),
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<BaseResponse>() {});

        BaseResponse baseResponse = CommonUtil.getGenericObject(responseEntity.getBody(),BaseResponse.class);
        return baseResponse;
    }

    private HttpHeaders prepareHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.ACCEPT_CHARSET, StandardCharsets.UTF_8.name());
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
