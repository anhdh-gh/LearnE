package source.third_party.question.service;

import source.dto.request.questionBank.*;
import source.dto.response.BaseResponse;

public interface QuestionBankServiceThirdParty {

    BaseResponse createQuestion(QuestionDto request) throws Exception;

    BaseResponse updateQuestion(QuestionDto request) throws Exception;

    BaseResponse deleteQuestionById(DeleteQuestionByIdRequestDto request) throws Exception;

    BaseResponse getQuestionById(GetQuestionByIdRequestDto request) throws Exception;

    BaseResponse getAllQuestion(GetAllQuestionDto request) throws Exception;

    BaseResponse getRankQuestion(GetRankQuestionDto request) throws Exception;

    BaseResponse saveTestResult(TestResultDto request) throws Exception;

    BaseResponse searchQuestion(SearchQuestionRequestDto request) throws Exception;
}
