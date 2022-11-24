package source.third_party.studyset.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import source.constant.RequestKeyConstant;
import source.dto.request.BasicRequest;
import source.dto.response.BaseResponse;
import source.third_party.studyset.constant.RouterStudysetThirdPartyConstant;
import source.third_party.studyset.dto.request.StudysetGetByIdsRequestDto;
import source.util.JsonUtil;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

@Service
public class StudysetThirdPartyServiceImpl implements StudysetThirdPartyService {

    @Value("${service.studyset.baseurl}")
    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;

    public static HttpEntity<BasicRequest> getHeader(BasicRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set(RequestKeyConstant.X_REQUEST_ID, request.getRequestId());
        headers.set(RequestKeyConstant.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.ACCEPT_CHARSET, StandardCharsets.UTF_8.name());
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(request, headers);
    }

    @Override
    public BaseResponse getStudysetByStudysetIds(StudysetGetByIdsRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterStudysetThirdPartyConstant.GET_STUDYSET_BY_IDS),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {
        });

        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }
}
