package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import source.constant.RequestKeyConstant;
import source.constant.RouterConstant;
import source.dto.request.QuestionUploadAvatarRequestDto;
import source.dto.response.BaseResponse;
import source.service.QuestionMultimediaService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class QuestionMultimediaController {

    @Autowired
    private QuestionMultimediaService questionMultimediaService;

    @PostMapping(RouterConstant.QUESTION_UPLOAD)
    public BaseResponse uploadAvatar(@Valid QuestionUploadAvatarRequestDto requestDto, HttpServletRequest request) throws Exception {
        requestDto.setRequestId((String) request.getAttribute(RequestKeyConstant.REQUEST_ID));
        return questionMultimediaService.uploadQuestion(requestDto);
    }
}