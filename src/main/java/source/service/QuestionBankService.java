package source.service;

import source.dto.request.BasicRequest;
import source.dto.request.CreateQuestionRequestDto;
import source.dto.request.GetQuestionByQuestionIdRequestDto;
import source.dto.response.BaseResponse;
import source.entity.Question;

public interface QuestionBankService {

    public BaseResponse getAllQuestions(BasicRequest request) throws Exception;

    public BaseResponse getQuestionByQuestionId(GetQuestionByQuestionIdRequestDto request) throws Exception;

    public BaseResponse createQuestion(CreateQuestionRequestDto request) throws Exception;
}
