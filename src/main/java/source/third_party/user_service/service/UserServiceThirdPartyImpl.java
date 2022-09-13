package source.third_party.user_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import source.dto.request.UserSignupRequestDto;
import source.dto.response.BaseResponse;
import source.third_party.user_service.constant.RouterUserServiceConstant;
import source.third_party.user_service.dto.request.UserSignupThirdPartyRequestDto;
import source.util.JsonUtil;

import java.nio.charset.StandardCharsets;

@Service
public class UserServiceThirdPartyImpl implements UserServiceThirdParty {

    @Value("${service.user.baseurl}")
    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public BaseResponse createUser(UserSignupThirdPartyRequestDto request) throws Exception {
        HttpEntity<Object> httpEntity = new HttpEntity<>(request, prepareHeaders());
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterUserServiceConstant.USER_CREATE),
            HttpMethod.POST,
            httpEntity,
            new ParameterizedTypeReference<BaseResponse>() {});

        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    private HttpHeaders prepareHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.ACCEPT_CHARSET, StandardCharsets.UTF_8.name());
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}