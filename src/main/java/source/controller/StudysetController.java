package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import source.annotation.LogsActivityAnnotation;
import source.constant.RouterConstant;
import source.dto.request.AddOrUpdateExaminationRequestDto;
import source.dto.request.CreateStudysetRequestDto;
import source.dto.request.GetStudysetByIdRequestDto;
import source.dto.response.BaseResponse;
import source.service.ExaminationService;
import source.service.StudysetService;

@RestController
public class StudysetController {

    @Autowired
    private StudysetService studysetService;

    @Autowired
    private ExaminationService examinationService;

    @PostMapping(RouterConstant.CREATE_STUDYSET)
    @LogsActivityAnnotation
    public BaseResponse createStudyset(@RequestBody CreateStudysetRequestDto request) throws Exception {
        return studysetService.createStudyset(request);
    }

    @PostMapping(RouterConstant.ADD_OR_UPDATE_EXAMINATION)
    @LogsActivityAnnotation
    public BaseResponse addOrUpdateExaminationRequestDto(@RequestBody AddOrUpdateExaminationRequestDto request) throws Exception {
        return examinationService.addOrUpdateExaminationRequestDto(request);
    }

    @PostMapping(RouterConstant.GET_STUDYSET_BY_ID)
    @LogsActivityAnnotation
    public BaseResponse getStudysetById(@RequestBody GetStudysetByIdRequestDto request) throws Exception {
        return studysetService.getStudysetById(request);
    }
}
