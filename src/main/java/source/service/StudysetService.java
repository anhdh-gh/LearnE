package source.service;

import source.dto.request.CreateStudysetRequestDto;
import source.dto.request.GetStudysetByIdRequestDto;
import source.dto.response.BaseResponse;

public interface StudysetService {

    BaseResponse createStudyset(CreateStudysetRequestDto request) throws Exception;

    BaseResponse getStudysetById(GetStudysetByIdRequestDto request) throws Exception;
}
