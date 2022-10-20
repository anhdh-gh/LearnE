package source.service;

import source.dto.request.AddOrUpdateExaminationRequestDto;
import source.dto.request.CreateStudysetRequestDto;
import source.dto.response.BaseResponse;

public interface ExaminationService {

    BaseResponse addOrUpdateExaminationRequestDto(AddOrUpdateExaminationRequestDto request) throws Exception;
}
