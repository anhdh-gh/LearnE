package source.third_party.studyset.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import source.dto.request.studyset.*;
import source.dto.response.BaseResponse;
import source.third_party.studyset.constant.RouterStudysetThirdPartyConstant;
import source.util.JsonUtil;

import static source.util.HttpUtil.getHeader;

@Service
public class StudysetServiceThirdPartyImpl implements StudysetServiceThirdParty {

    @Value("${service.studyset.baseurl}")
    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public BaseResponse createStudyset(StudysetDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterStudysetThirdPartyConstant.CREATE_STUDYSET),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {});
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse updateStudyset(StudysetDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterStudysetThirdPartyConstant.UPDATE_STUDYSET),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {});
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse deleteStudyset(DeleteStudysetByIdRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterStudysetThirdPartyConstant.DELETE_STUDYSET_BY_ID),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {});
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse getStudysetById(GetStudysetByIdRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterStudysetThirdPartyConstant.GET_STUDYSET_BY_ID),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {});
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse saveTestResult(TestResultDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterStudysetThirdPartyConstant.SAVE_STUDYSET_TEST_RESULT),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {});
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse getAllStudysetByOwnerUserId(GetAllStudysetByOwnerUserIdRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterStudysetThirdPartyConstant.GET_ALL_STUDYSET_BY_OWNER_USER_ID),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {});
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse getAllStudyset(GetAllStudysetRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterStudysetThirdPartyConstant.GET_ALL_STUDYSET),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {});
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse searchAllStudysetByOwnerUserId(SearchAllStudysetByOwnerUserIdRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterStudysetThirdPartyConstant.SEARCH_ALL_STUDYSET_BY_OWNER_USER_ID),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {});
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse searchAllStudyset(SearchAllStudysetRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterStudysetThirdPartyConstant.SEARCH_ALL_STUDYSET),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {});
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse getRankStudyset(GetRankStudysetRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterStudysetThirdPartyConstant.GET_RANK_STUDYSET),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {});
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse checkOwnerStudysetValid(CheckOwnerStudysetValidRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterStudysetThirdPartyConstant.CHECK_OWNER_STUDYSET_VALID),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {});
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }
}
