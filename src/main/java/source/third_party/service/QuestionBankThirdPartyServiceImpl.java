package source.third_party.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import source.dto.response.BaseResponse;
import source.third_party.constant.RouterCourseServiceConstant;
import source.third_party.dto.request.GetQuestionsByIdsRequestDto;
import source.util.JsonUtil;

import java.util.List;

import static source.util.HttpUtil.getHeader;

@Service
public class QuestionBankThirdPartyServiceImpl implements QuestionBankThirdPartyService {

    @Value("${service.question-bank.baseurl}")
    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public BaseResponse getQuestionsByIds(List<String> questionIds) {

        GetQuestionsByIdsRequestDto requestDto = new GetQuestionsByIdsRequestDto(questionIds);

        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
            String.format("%s%s", baseUrl, RouterCourseServiceConstant.QUESTION_GET_BY_IDS),
            HttpMethod.POST,
            getHeader(requestDto),
            new ParameterizedTypeReference<BaseResponse>() {
            });

        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }
}
