package source.third_party.question_bank.service;

import source.dto.response.BaseResponse;
import source.third_party.question_bank.dto.request.CreateListQuestionsRequestDto;
import source.third_party.question_bank.dto.request.QuestionGetByIdsRequestDto;

public interface QuestionBankThirdPartyService {

    BaseResponse createQuestionsList(CreateListQuestionsRequestDto request) throws Exception;

    BaseResponse getQuestionByQuestionIds(QuestionGetByIdsRequestDto request) throws Exception;
}
