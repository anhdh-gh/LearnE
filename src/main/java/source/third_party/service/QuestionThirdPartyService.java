package source.third_party.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import source.dto.request.QuestionGetByIdsRequestDto;
import source.dto.response.BaseResponse;

public interface QuestionThirdPartyService {

    public BaseResponse getQuestionsByIds(QuestionGetByIdsRequestDto requestDto) throws Exception;
}
