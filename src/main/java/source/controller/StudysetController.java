package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import source.annotation.LogsActivityAnnotation;
import source.constant.RouterConstant;
import source.dto.request.studyset.*;
import source.dto.response.BaseResponse;
import source.entity.enumeration.Role;
import source.service.studyset_service.StudysetService;

@RestController
public class StudysetController {

    @Autowired
    private StudysetService studysetService;

    @PostMapping(RouterConstant.CREATE_STUDYSET)
    @LogsActivityAnnotation
    public BaseResponse createStudyset(@RequestBody StudysetDto request) throws Exception {
        request.setOwnerUserId(request.getUserAuthId());
        return studysetService.createStudyset(request);
    }

    @PostMapping(RouterConstant.SAVE_STUDYSET_TEST_RESULT)
    @LogsActivityAnnotation
    public BaseResponse saveTestResult(@RequestBody TestResultDto request) throws Exception {
        request.setUserId(request.getUserAuthId());
        return studysetService.saveTestResult(request);
    }

    @PostMapping(RouterConstant.GET_STUDYSET_BY_ID)
    @LogsActivityAnnotation
    public BaseResponse getStudysetById(@RequestBody GetStudysetByIdRequestDto request) throws Exception {
        request.setUserId(request.getUserAuthId());
        return studysetService.getStudysetById(request);
    }

    @PostMapping(RouterConstant.GET_ALL_STUDYSET_BY_OWNER_USER_ID)
    @LogsActivityAnnotation
    public BaseResponse getAllStudysetByOwnerUserId(@RequestBody GetAllStudysetByOwnerUserIdRequestDto request) throws Exception {
        request.setUserId(request.getUserAuthId());
        return studysetService.getAllStudysetByOwnerUserId(request);
    }

    @PostMapping(RouterConstant.GET_ALL_STUDYSET)
    @LogsActivityAnnotation
    public BaseResponse getAllStudyset(@RequestBody GetAllStudysetByOwnerUserIdRequestDto request) throws Exception {
        request.setUserId(request.getUserAuthId());
        return studysetService.getAllStudyset(request);
    }

    @PostMapping(RouterConstant.SEARCH_ALL_STUDYSET_BY_OWNER_USER_ID)
    @LogsActivityAnnotation
    public BaseResponse searchAllStudysetByOwnerUserId(@RequestBody SearchAllStudysetByOwnerUserIdRequestDto request) throws Exception {
        request.setUserId(request.getUserAuthId());
        return studysetService.searchAllStudysetByOwnerUserId(request);
    }

    @PostMapping(RouterConstant.SEARCH_ALL_STUDYSET)
    @LogsActivityAnnotation
    public BaseResponse searchAllStudyset(@RequestBody SearchAllStudysetByOwnerUserIdRequestDto request) throws Exception {
        request.setUserId(request.getUserAuthId());
        return studysetService.searchAllStudyset(request);
    }

    @PostMapping(RouterConstant.DELETE_STUDYSET_BY_ID)
    @LogsActivityAnnotation
    public BaseResponse deleteStudyset(@RequestBody DeleteStudysetByIdRequestDto request) throws Exception {
        if (!request.getUserAuthRole().equals(Role.ADMIN.getValue())) {
            request.setOwnerUserId(request.getUserAuthId());
        }
        return studysetService.deleteStudyset(request);
    }

    @PostMapping(RouterConstant.UPDATE_STUDYSET)
    @LogsActivityAnnotation
    public BaseResponse updateStudyset(@RequestBody StudysetDto request) throws Exception {
        if (!request.getUserAuthRole().equals(Role.ADMIN.getValue())) {
            request.setOwnerUserId(request.getUserAuthId());
        }
        return studysetService.updateStudyset(request);
    }

    @PostMapping(RouterConstant.GET_RANK_STUDYSET)
    @LogsActivityAnnotation
    public BaseResponse getRankStudyset(@RequestBody GetRankStudysetRequestDto request) throws Exception {
        return studysetService.getRankStudyset(request);
    }

    @PostMapping(RouterConstant.CHECK_OWNER_STUDYSET_VALID)
    @LogsActivityAnnotation
    public BaseResponse checkOwnerStudysetValid(@RequestBody CheckOwnerStudysetValidRequestDto request) throws Exception {
        if (!request.getUserAuthRole().equals(Role.ADMIN.getValue())) {
            request.setOwnerUserId(request.getUserAuthId());
        }
        return studysetService.checkOwnerStudysetValid(request);
    }
}