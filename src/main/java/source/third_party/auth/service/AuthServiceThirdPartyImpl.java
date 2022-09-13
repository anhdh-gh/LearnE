package source.third_party.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import source.dto.request.UserSignInRequestDto;
import source.dto.request.UserSignUpRequestDto;
import source.dto.response.BaseResponse;
import source.third_party.auth.constant.RouterAuthServiceConstant;
import source.util.CommonUtil;

import java.nio.charset.StandardCharsets;

@Service
public class AuthServiceThirdPartyImpl implements AuthServiceThirdParty {
    @Value("${service.auth.baseurl}")
    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public BaseResponse signIn(UserSignInRequestDto request) throws Exception {
        HttpEntity<Object> httpEntity = new HttpEntity<>(request, prepareHeaders());
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.SIGN_IN),
            HttpMethod.POST,
            httpEntity,
            new ParameterizedTypeReference<BaseResponse>() {});

        return CommonUtil.getGenericObject(responseEntity.getBody(),BaseResponse.class);
    }

    @Override
    public BaseResponse signUp(UserSignUpRequestDto request) throws Exception{
        HttpEntity<Object> httpEntity = new HttpEntity<>(request, prepareHeaders());
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterAuthServiceConstant.SIGN_UP),
            HttpMethod.POST,
            httpEntity,
            new ParameterizedTypeReference<BaseResponse>() {});

        return CommonUtil.getGenericObject(responseEntity.getBody(),BaseResponse.class);
    }

    private HttpHeaders prepareHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.ACCEPT_CHARSET, StandardCharsets.UTF_8.name());
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
