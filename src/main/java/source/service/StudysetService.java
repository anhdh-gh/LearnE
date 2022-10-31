package source.service;

import source.dto.StudysetDto;
import source.dto.TestResultDto;
import source.dto.request.*;
import source.dto.response.BaseResponse;

public interface StudysetService {

    BaseResponse createStudyset(StudysetDto request) throws Exception;

    BaseResponse updateStudyset(StudysetDto request) throws Exception;

    BaseResponse deleteStudyset(DeleteStudysetByIdRequestDto request) throws Exception;

    BaseResponse getStudysetById(GetStudysetByIdRequestDto request) throws Exception;

    BaseResponse saveTestResult(TestResultDto request) throws Exception;

    BaseResponse getAllStudysetByOwnerUserId(GetAllStudysetByOwnerUserIdRequestDto request) throws Exception;

    BaseResponse getAllStudyset(GetAllStudysetRequestDto request) throws Exception;

    BaseResponse searchAllStudyset(SearchAllStudysetRequestDto request) throws Exception;

    BaseResponse searchAllStudysetByOwnerUserId(SearchAllStudysetByOwnerUserIdRequestDto request) throws Exception;

    BaseResponse getRankStudyset(GetRankStudysetRequestDto request) throws Exception;

    BaseResponse checkOwnerStudysetValid(CheckOwnerStudysetValidRequestDto request) throws Exception;
}
