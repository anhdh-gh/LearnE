package source.service;

import source.dto.request.*;
import source.dto.response.BaseResponse;

public interface QuestionBankService {

    BaseResponse getQuestionByQuestionId(GetQuestionByQuestionIdRequestDto request) throws Exception;

    BaseResponse createQuestionsList(CreateListQuestionsRequestDto request) throws Exception;

    BaseResponse getAllQuestion(QuestionGetAllRequestDto request) throws Exception;

    BaseResponse getQuestionByQuestionIds(QuestionGetByIdsRequestDto request) throws Exception;
}
