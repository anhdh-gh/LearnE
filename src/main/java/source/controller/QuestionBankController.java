package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
    public BaseResponse getAllQuestion(@RequestBody QuestionGetAllRequestDto request) throws Exception {
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
    @PostMapping(RouterConstant.QUESTION_DELETE_BY_GROUP_QUESTION_ID)
    public BaseResponse deleteQuestionsListByGroupId(@RequestBody DeleteListQuestionsByGroupIdRequestDto request) throws Exception {
        return questionBankService.deleteQuestionsListByGroupId(request);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.QUESTION_GET_BY_GROUP_QUESTION_ID)
    public BaseResponse getQuestionsListByGroupId(@RequestBody GetListQuestionsByGroupIdRequestDto request) throws Exception {
        return questionBankService.getQuestionsListByGroupId(request);
    }

    @LogsActivityAnnotation
    @PostMapping(RouterConstant.QUESTION_GET_LIST_BY_QUESTION_TYPE_AND_LIMIT)
    public BaseResponse getQuestionsListByQuestionTypeAndLimit(@RequestBody QuestionGetListRequestDto request) throws Exception {
        return questionBankService.getQuestionsListByQuestionTypeAndLimit(request);
    }
}