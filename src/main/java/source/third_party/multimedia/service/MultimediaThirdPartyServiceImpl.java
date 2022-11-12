package source.third_party.multimedia.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import source.dto.response.BaseResponse;
import source.third_party.multimedia.BaseService;
import source.third_party.multimedia.constant.RouterConstant;
import source.third_party.multimedia.dto.request.QuestionCheckExistRequestDto;
import source.third_party.multimedia.dto.request.QuestionDeleteByGroupIdRequestDto;
import source.util.JsonUtil;

@Service
public class MultimediaThirdPartyServiceImpl extends BaseService implements MultimediaThirdPartyService {

    @Value("${service.multimedia.baseurl}")
    private String baseUrl;

    @Override
    public BaseResponse checkQuestionExist(QuestionCheckExistRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterConstant.QUESTION_CHECK_EXIST),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {});
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse deleteQuestionByGroupId(QuestionDeleteByGroupIdRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterConstant.QUESTION_DELETE_BY_GROUP_ID),
            HttpMethod.POST,
            getHeader(request),
            new ParameterizedTypeReference<BaseResponse>() {});
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }
}
