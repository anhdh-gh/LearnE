package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import source.anotation.LogsActivityAnnotation;
import source.constant.RequestKeyConstant;
import source.constant.RouterConstant;
import source.dto.request.QuestionCheckExistRequestDto;
import source.dto.request.QuestionDeleteByGroupIdRequestDto;
import source.dto.request.QuestionUploadRequestDto;
import source.dto.response.BaseResponse;
import source.service.QuestionMultimediaService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class QuestionMultimediaController {

    @Autowired
    private QuestionMultimediaService questionMultimediaService;

    @PostMapping(RouterConstant.QUESTION_UPLOAD)
    public BaseResponse uploadQuestion(@Valid QuestionUploadRequestDto requestDto, HttpServletRequest request) throws Exception {
        requestDto.setRequestId((String) request.getAttribute(RequestKeyConstant.REQUEST_ID));
        return questionMultimediaService.uploadQuestion(requestDto);
    }

    @PostMapping(RouterConstant.QUESTION_CHECK_EXIST)
    @LogsActivityAnnotation
    public BaseResponse checkQuestionExist(@Valid @RequestBody QuestionCheckExistRequestDto requestDto) throws Exception {
        return questionMultimediaService.checkQuestionExist(requestDto);
    }

    @PostMapping(RouterConstant.QUESTION_DELETE_BY_GROUP_ID)
    @LogsActivityAnnotation
    public BaseResponse deleteQuestionByGroupId(@Valid @RequestBody QuestionDeleteByGroupIdRequestDto requestDto) throws Exception {
        return questionMultimediaService.deleteQuestionByGroupId(requestDto);
    }
}