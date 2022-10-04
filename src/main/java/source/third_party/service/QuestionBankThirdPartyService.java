package source.third_party.service;

import source.dto.response.BaseResponse;

import java.util.List;

public interface QuestionBankThirdPartyService {
    public BaseResponse getQuestionsByIds(List<String> questionIds);
}
