package source.service;

import source.dto.QuestionDto;
import source.dto.TestResultDto;
import source.dto.request.*;
import source.dto.response.BaseResponse;

public interface QuestionBankService {

    BaseResponse createQuestion(QuestionDto request) throws Exception;

    BaseResponse updateQuestion(QuestionDto request) throws Exception;

    BaseResponse deleteQuestionById(DeleteQuestionByIdRequestDto request) throws Exception;

    BaseResponse getQuestionById(GetQuestionByIdRequestDto request) throws Exception;

    BaseResponse getAllQuestion(GetAllQuestionDto request) throws Exception;

    BaseResponse getRankQuestion(GetRankQuestionDto request) throws Exception;

    BaseResponse saveTestResult(TestResultDto request) throws Exception;

    BaseResponse getQuestionByQuestionIds(QuestionGetByIdsRequestDto request) throws Exception;

    BaseResponse searchQuestion(SearchQuestionRequestDto request) throws Exception;
}
