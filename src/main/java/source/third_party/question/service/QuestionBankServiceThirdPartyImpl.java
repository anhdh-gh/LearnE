package source.third_party.question.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import source.dto.request.questionBank.*;
import source.dto.response.BaseResponse;
import source.third_party.question.constant.RouterQuestionBankServiceConstant;
import source.util.JsonUtil;

import static source.util.HttpUtil.getHeader;

@Service
public class QuestionBankServiceThirdPartyImpl implements QuestionBankServiceThirdParty {

    @Value("${service.question-bank.baseurl}")
    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public BaseResponse getQuestionByQuestionId(GetQuestionByQuestionIdRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
                String.format("%s%s", baseUrl, RouterQuestionBankServiceConstant.QUESTION_GET_BY_QUESTION_ID),
                HttpMethod.POST,
                getHeader(request),
                new ParameterizedTypeReference<BaseResponse>() {
                });
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse createQuestionsList(CreateListQuestionsRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
                String.format("%s%s", baseUrl, RouterQuestionBankServiceConstant.QUESTION_CREATE_LIST),
                HttpMethod.POST,
                getHeader(request),
                new ParameterizedTypeReference<BaseResponse>() {
                });
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse getAllQuestion(QuestionGetAllRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
                String.format("%s%s", baseUrl, RouterQuestionBankServiceConstant.QUESTION_GET_ALL),
                HttpMethod.POST,
                getHeader(request),
                new ParameterizedTypeReference<BaseResponse>() {
                });
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse getQuestionByQuestionIds(QuestionGetByIdsRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
                String.format("%s%s", baseUrl, RouterQuestionBankServiceConstant.QUESTION_GET_BY_IDS),
                HttpMethod.POST,
                getHeader(request),
                new ParameterizedTypeReference<BaseResponse>() {
                });
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse deleteQuestionsListByGroupId(DeleteListQuestionsByGroupIdRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
                String.format("%s%s", baseUrl, RouterQuestionBankServiceConstant.QUESTION_DELETE_BY_GROUP_QUESTION_ID),
                HttpMethod.POST,
                getHeader(request),
                new ParameterizedTypeReference<BaseResponse>() {
                });
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }

    @Override
    public BaseResponse getQuestionsListByGroupId(GetListQuestionsByGroupIdRequestDto request) throws Exception {
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(
                String.format("%s%s", baseUrl, RouterQuestionBankServiceConstant.QUESTION_GET_BY_GROUP_QUESTION_ID),
                HttpMethod.POST,
                getHeader(request),
                new ParameterizedTypeReference<BaseResponse>() {
                });
        return JsonUtil.getGenericObject(responseEntity.getBody(), BaseResponse.class);
    }
}
