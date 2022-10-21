package source.third_party.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import source.dto.response.BaseResponse;
import source.third_party.user.constant.RouterUserServiceConstant;
import source.third_party.user.dto.request.UserGetByIdRequestDto;
import source.third_party.user.dto.request.UserGetListByIdsRequestDto;
import source.util.JsonUtil;

import static source.util.HttpUtil.getHeader;

@Service
public class UserServiceThirdPartyImpl implements UserServiceThirdParty {

    @Value("${service.user.baseurl}")
    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public BaseResponse getUserById(UserGetByIdRequestDto userGetByIdRequestDto) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterUserServiceConstant.GET_USER_BY_ID),
            HttpMethod.POST,
            getHeader(userGetByIdRequestDto),
            new ParameterizedTypeReference<BaseResponse>() {});
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse getUserByUserIds(UserGetListByIdsRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterUserServiceConstant.GET_USER_LIST_BY_IDS),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {});
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }
}