package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import source.annotation.LogsActivityAnnotation;
import source.constant.RouterConstant;
import source.dto.StudysetDto;
import source.dto.request.DeleteStudysetByIdRequestDto;
import source.dto.request.GetAllStudysetByOwnerUserIdRequestDto;

import source.dto.request.GetStudysetByIdRequestDto;
import source.dto.request.SaveStudysetScore;
import source.dto.response.BaseResponse;
import source.service.StudysetService;

@RestController
public class StudysetController {

    @Autowired
    private StudysetService studysetService;

    @PostMapping(RouterConstant.CREATE_STUDYSET)
    @LogsActivityAnnotation
    public BaseResponse createStudyset(@RequestBody StudysetDto request) throws Exception {
        return studysetService.createStudyset(request);
    }

    @PostMapping(RouterConstant.SAVE_STUDYSET_SCORE)
    @LogsActivityAnnotation
    public BaseResponse saveStudysetScore(@RequestBody SaveStudysetScore request) throws Exception {
        return studysetService.saveStudysetScore(request);
    }

    @PostMapping(RouterConstant.GET_STUDYSET_BY_ID)
    @LogsActivityAnnotation
    public BaseResponse getStudysetById(@RequestBody GetStudysetByIdRequestDto request) throws Exception {
        return studysetService.getStudysetById(request);
    }

    @PostMapping(RouterConstant.GET_ALL_STUDYSET_BY_OWNER_USER_ID)
    @LogsActivityAnnotation
    public BaseResponse getAllStudysetByOwnerUserId(@RequestBody GetAllStudysetByOwnerUserIdRequestDto request) throws Exception {
        return studysetService.getAllStudysetByOwnerUserId(request);
    }

    @PostMapping(RouterConstant.GET_ALL_STUDYSET)
    @LogsActivityAnnotation
    public BaseResponse getAllStudyset(@RequestBody GetAllStudysetByOwnerUserIdRequestDto request) throws Exception {
        return studysetService.getAllStudyset(request);
    }

    @PostMapping(RouterConstant.DELETE_STUDYSET_BY_ID)
    @LogsActivityAnnotation
    public BaseResponse getAllStudyset(@RequestBody DeleteStudysetByIdRequestDto request) throws Exception {
        return studysetService.deleteStudyset(request);
    }

    @PostMapping(RouterConstant.UPDATE_STUDYSET)
    @LogsActivityAnnotation
    public BaseResponse updateStudyset(@RequestBody StudysetDto request) throws Exception {
        return studysetService.updateStudyset(request);
    }
}
