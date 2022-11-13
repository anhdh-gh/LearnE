package source.third_party.question.service;

import source.dto.request.questionBank.*;
import source.dto.response.BaseResponse;

public interface QuestionBankServiceThirdParty {

    BaseResponse getQuestionByQuestionId(GetQuestionByQuestionIdRequestDto request) throws Exception;

    BaseResponse createQuestionsList(CreateListQuestionsRequestDto request) throws Exception;

    BaseResponse getAllQuestion(QuestionGetAllRequestDto request) throws Exception;

    BaseResponse getQuestionByQuestionIds(QuestionGetByIdsRequestDto request) throws Exception;

    BaseResponse deleteQuestionsListByGroupId(DeleteListQuestionsByGroupIdRequestDto request) throws Exception;

    BaseResponse getQuestionsListByGroupId(GetListQuestionsByGroupIdRequestDto request) throws Exception;
}
