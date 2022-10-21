package source.third_party.studyset.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import source.dto.request.studyset.AddOrUpdateExaminationRequestDto;
import source.dto.request.studyset.CreateStudysetRequestDto;
import source.dto.request.studyset.GetStudysetByIdRequestDto;
import source.dto.response.BaseResponse;
import source.third_party.studyset.constant.RouterStudysetServiceConstant;
import source.util.JsonUtil;

import static source.util.HttpUtil.getHeader;

@Service
public class StudysetServiceThirdPartyImpl implements StudysetServiceThirdParty {

    @Value("${service.studyset.baseurl}")
    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public BaseResponse createStudyset(CreateStudysetRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterStudysetServiceConstant.CREATE_STUDYSET),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {});
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse getStudysetById(GetStudysetByIdRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterStudysetServiceConstant.GET_STUDYSET_BY_ID),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {});
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse addOrUpdateExaminationRequestDto(AddOrUpdateExaminationRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterStudysetServiceConstant.ADD_OR_UPDATE_EXAMINATION),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {});
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }
}