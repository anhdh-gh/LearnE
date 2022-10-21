package source.third_party.studyset.service;

import source.dto.request.studyset.AddOrUpdateExaminationRequestDto;
import source.dto.request.studyset.CreateStudysetRequestDto;
import source.dto.request.studyset.GetStudysetByIdRequestDto;
import source.dto.response.BaseResponse;

public interface StudysetServiceThirdParty {

    BaseResponse createStudyset(CreateStudysetRequestDto request) throws Exception;

    BaseResponse getStudysetById(GetStudysetByIdRequestDto request) throws Exception;

    BaseResponse addOrUpdateExaminationRequestDto(AddOrUpdateExaminationRequestDto request) throws Exception;
}
