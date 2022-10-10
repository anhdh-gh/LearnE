package source.service.question_bank_service;

import source.dto.request.CreateQuestionRequestDto;
import source.dto.request.GetQuestionByQuestionIdRequestDto;
import source.dto.request.QuestionGetAllRequestDto;
import source.dto.request.UserSignUpRequestDto;
import source.dto.response.BaseResponse;

public interface QuestionBankService {

    public BaseResponse getQuestionByQuestionId(GetQuestionByQuestionIdRequestDto request) throws Exception;

    public BaseResponse createQuestion(CreateQuestionRequestDto request) throws Exception;

    public BaseResponse getAllQuestion(QuestionGetAllRequestDto request) throws Exception;
}
