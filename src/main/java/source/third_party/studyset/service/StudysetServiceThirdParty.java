package source.third_party.studyset.service;

import source.dto.request.studyset.*;
import source.dto.response.BaseResponse;

public interface StudysetServiceThirdParty {

    BaseResponse createStudyset(StudysetDto request) throws Exception;

    BaseResponse updateStudyset(StudysetDto request) throws Exception;

    BaseResponse deleteStudyset(DeleteStudysetByIdRequestDto request) throws Exception;

    BaseResponse getStudysetById(GetStudysetByIdRequestDto request) throws Exception;

    BaseResponse saveTestResult(TestResultDto request) throws Exception;

    BaseResponse getAllStudysetByOwnerUserId(GetAllStudysetByOwnerUserIdRequestDto request) throws Exception;

    BaseResponse getAllStudyset(GetAllStudysetRequestDto request) throws Exception;

    BaseResponse getRankStudyset(GetRankStudysetRequestDto request) throws Exception;

    BaseResponse checkOwnerStudysetValid(CheckOwnerStudysetValidRequestDto request) throws Exception;
}
