package source.service.studyset_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.dto.request.studyset.*;
import source.dto.response.BaseResponse;
import source.third_party.studyset.service.StudysetServiceThirdParty;

@Service
public class StudysetServiceImpl implements StudysetService {

    @Autowired
    private StudysetServiceThirdParty studysetServiceThirdParty;

    @Override
    public BaseResponse createStudyset(StudysetDto request) throws Exception {
        return studysetServiceThirdParty.createStudyset(request);
    }

    @Override
    public BaseResponse updateStudyset(StudysetDto request) throws Exception {
        return studysetServiceThirdParty.updateStudyset(request);
    }

    @Override
    public BaseResponse deleteStudyset(DeleteStudysetByIdRequestDto request) throws Exception {
        return studysetServiceThirdParty.deleteStudyset(request);
    }

    @Override
    public BaseResponse getStudysetById(GetStudysetByIdRequestDto request) throws Exception {
        return studysetServiceThirdParty.getStudysetById(request);
    }

    @Override
    public BaseResponse saveTestResult(TestResultDto request) throws Exception {
        return studysetServiceThirdParty.saveTestResult(request);
    }

    @Override
    public BaseResponse getAllStudysetByOwnerUserId(GetAllStudysetByOwnerUserIdRequestDto request) throws Exception {
        return studysetServiceThirdParty.getAllStudysetByOwnerUserId(request);
    }

    @Override
    public BaseResponse getAllStudyset(GetAllStudysetRequestDto request) throws Exception {
        return studysetServiceThirdParty.getAllStudyset(request);
    }

    @Override
    public BaseResponse searchAllStudysetByOwnerUserId(SearchAllStudysetByOwnerUserIdRequestDto request) throws Exception {
        return studysetServiceThirdParty.searchAllStudysetByOwnerUserId(request);
    }

    @Override
    public BaseResponse searchAllStudyset(SearchAllStudysetRequestDto request) throws Exception {
        return studysetServiceThirdParty.searchAllStudyset(request);
    }

    @Override
    public BaseResponse getRankStudyset(GetRankStudysetRequestDto request) throws Exception {
        return studysetServiceThirdParty.getRankStudyset(request);
    }

    @Override
    public BaseResponse checkOwnerStudysetValid(CheckOwnerStudysetValidRequestDto request) throws Exception {
        return studysetServiceThirdParty.checkOwnerStudysetValid(request);
    }
}
