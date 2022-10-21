package source.service.studyset_service;

import source.dto.request.studyset.AddOrUpdateExaminationRequestDto;
import source.dto.request.studyset.CreateStudysetRequestDto;
import source.dto.request.studyset.GetStudysetByIdRequestDto;
import source.dto.response.BaseResponse;

public interface StudysetService {

    BaseResponse createStudyset(CreateStudysetRequestDto request) throws Exception;

    BaseResponse getStudysetById(GetStudysetByIdRequestDto request) throws Exception;

    BaseResponse addOrUpdateExaminationRequestDto(AddOrUpdateExaminationRequestDto request) throws Exception;
}
