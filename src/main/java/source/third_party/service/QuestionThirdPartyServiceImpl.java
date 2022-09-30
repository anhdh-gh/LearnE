package source.third_party.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import source.constant.RouterConstant;
import source.dto.request.QuestionGetByIdsRequestDto;
import source.dto.response.BaseResponse;
import source.util.JsonUtil;

import static source.util.HttpUtil.getHeader;

@Service
public class QuestionThirdPartyServiceImpl implements QuestionThirdPartyService{

    @Value("${service.question-bank.baseurl}")
    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public BaseResponse getQuestionsByIds(QuestionGetByIdsRequestDto requestDto) {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterConstant.QUESTION_GET_BY_IDS),
            HttpMethod.POST,
            getHeader(requestDto),
            new ParameterizedTypeReference<BaseResponse>() {});
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }
}
