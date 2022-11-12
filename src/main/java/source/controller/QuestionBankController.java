package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import source.annotation.LogsActivityAnnotation;
import source.constant.RouterConstant;
import source.dto.request.*;
import source.dto.response.BaseResponse;
import source.service.QuestionBankService;

@RestController
public class QuestionBankController {

    @Autowired
    private QuestionBankService questionBankService;

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.QUESTION_GET_BY_QUESTION_ID)
    public BaseResponse getQuestionByQuestionId(@RequestBody GetQuestionByQuestionIdRequestDto request) throws Exception {
        return questionBankService.getQuestionByQuestionId(request);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.QUESTION_GET_ALL)
    public BaseResponse getQuestion(@RequestBody QuestionGetAllRequestDto request) throws Exception {
        return questionBankService.getAllQuestion(request);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.QUESTION_GET_BY_IDS)
    public BaseResponse getQuestionByIds(@RequestBody QuestionGetByIdsRequestDto request) throws Exception {
        return questionBankService.getQuestionByQuestionIds(request);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.QUESTION_CREATE_LIST)
    public BaseResponse createQuestionsList(@RequestBody CreateListQuestionsRequestDto request) throws Exception {
        return questionBankService.createQuestionsList(request);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.QUESTION_UPDATE_LIST)
    public BaseResponse updateQuestionsList(@RequestBody UpdateListQuestionsRequestDto request) throws Exception {
        return questionBankService.updateQuestionsList(request);
    }
}