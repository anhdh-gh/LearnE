package source.third_party.question.service;

import source.dto.request.CreateQuestionRequestDto;
import source.dto.request.GetQuestionByQuestionIdRequestDto;
import source.dto.request.QuestionGetAllRequestDto;
import source.dto.response.BaseResponse;

public interface QuestionBankServiceThirdParty {
    BaseResponse getQuestionByQuestionId(GetQuestionByQuestionIdRequestDto request) throws Exception;

    BaseResponse createQuestion(CreateQuestionRequestDto request) throws Exception;

    BaseResponse getAllQuestion(QuestionGetAllRequestDto request) throws Exception;
}
