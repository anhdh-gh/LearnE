package source.third_party.question.service;

import source.dto.request.user.CreateQuestionRequestDto;
import source.dto.request.question.GetQuestionByQuestionIdRequestDto;
import source.dto.request.question.QuestionGetAllRequestDto;
import source.dto.response.BaseResponse;

public interface QuestionBankServiceThirdParty {
    public BaseResponse getQuestionByQuestionId(GetQuestionByQuestionIdRequestDto request) throws Exception;

    public BaseResponse createQuestion(CreateQuestionRequestDto request) throws Exception;

    public BaseResponse getAllQuestion(QuestionGetAllRequestDto request) throws Exception;
}
