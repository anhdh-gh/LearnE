package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import source.constant.RouterConstant;
import source.dto.request.CreateQuestionRequestDto;
import source.dto.request.GetQuestionByQuestionIdRequestDto;
import source.dto.request.QuestionGetAllRequestDto;
import source.dto.response.BaseResponse;
import source.service.QuestionBankService;

@RestController
public class QuestionBankController {

    @Autowired
    private QuestionBankService questionBankService;

    @PostMapping(RouterConstant.QUESTION_CREATE)
    public BaseResponse createQuestion(@RequestBody CreateQuestionRequestDto request) throws Exception {
        return questionBankService.createQuestion(request);
    }

    @PostMapping(RouterConstant.QUESTION_GET_BY_QUESTION_ID)
    public BaseResponse getQuestionByQuestionId(@RequestBody GetQuestionByQuestionIdRequestDto request) throws Exception {
        return questionBankService.getQuestionByQuestionId(request);
    }

    @PostMapping(RouterConstant.QUESTION_GET_ALL)
    public BaseResponse getQuestion(@RequestBody QuestionGetAllRequestDto request) throws Exception {
        return questionBankService.getAllQuestion(request);
    }
}