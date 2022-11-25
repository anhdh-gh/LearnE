package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import source.annotation.LogsActivityAnnotation;
import source.constant.RouterConstant;
import source.dto.QuestionDto;
import source.dto.TestResultDto;
import source.dto.request.*;
import source.dto.response.BaseResponse;
import source.service.QuestionBankService;

@RestController
public class QuestionBankController {

    @Autowired
    private QuestionBankService questionBankService;

    @PostMapping(RouterConstant.QUESTION_CREATE)
    @LogsActivityAnnotation
    public BaseResponse createQuestion(@RequestBody QuestionDto request) throws Exception {
        return questionBankService.createQuestion(request);
    }

    @PostMapping(RouterConstant.QUESTION_UPDATE)
    @LogsActivityAnnotation
    public BaseResponse updateQuestion(@RequestBody QuestionDto request) throws Exception {
        return questionBankService.updateQuestion(request);
    }

    @PostMapping(RouterConstant.QUESTION_DELETE_BY_ID)
    @LogsActivityAnnotation
    public BaseResponse deleteQuestionById(@RequestBody DeleteQuestionByIdRequestDto request) throws Exception {
        return questionBankService.deleteQuestionById(request);
    }

    @PostMapping(RouterConstant.QUESTION_GET_BY_ID)
    @LogsActivityAnnotation
    public BaseResponse getQuestionById(@RequestBody GetQuestionByIdRequestDto request) throws Exception {
        return questionBankService.getQuestionById(request);
    }

    @PostMapping(RouterConstant.QUESTION_GET_ALL)
    @LogsActivityAnnotation
    public BaseResponse getAllQuestion(@RequestBody GetAllQuestionDto request) throws Exception {
        return questionBankService.getAllQuestion(request);
    }

    @PostMapping(RouterConstant.QUESTION_GET_RANK)
    @LogsActivityAnnotation
    public BaseResponse getRankQuestion(@RequestBody GetRankQuestionDto request) throws Exception {
        return questionBankService.getRankQuestion(request);
    }

    @PostMapping(RouterConstant.QUESTION_SAVE_TEST_RESULT)
    @LogsActivityAnnotation
    public BaseResponse saveTestResult(@RequestBody TestResultDto request) throws Exception {
        return questionBankService.saveTestResult(request);
    }

    @PostMapping(RouterConstant.QUESTION_GET_BY_IDS)
    @LogsActivityAnnotation
    public BaseResponse getQuestionByIds(@RequestBody QuestionGetByIdsRequestDto request) throws Exception {
        return questionBankService.getQuestionByQuestionIds(request);
    }

    @PostMapping(RouterConstant.SEARCH_QUESTION)
    @LogsActivityAnnotation
    public BaseResponse searchQuestion(@RequestBody SearchQuestionRequestDto request) throws Exception {
        return questionBankService.searchQuestion(request);
    }
}