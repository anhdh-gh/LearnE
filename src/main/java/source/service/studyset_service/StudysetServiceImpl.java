package source.service.studyset_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.dto.request.studyset.AddOrUpdateExaminationRequestDto;
import source.dto.request.studyset.CreateStudysetRequestDto;
import source.dto.request.studyset.GetStudysetByIdRequestDto;
import source.dto.response.BaseResponse;
import source.third_party.studyset.service.StudysetServiceThirdParty;

@Service
public class StudysetServiceImpl implements StudysetService {

    @Autowired
    private StudysetServiceThirdParty studysetServiceThirdParty;

    @Override
    public BaseResponse createStudyset(CreateStudysetRequestDto request) throws Exception {
        return studysetServiceThirdParty.createStudyset(request);
    }

    @Override
    public BaseResponse getStudysetById(GetStudysetByIdRequestDto request) throws Exception {
        return studysetServiceThirdParty.getStudysetById(request);
    }

    @Override
    public BaseResponse addOrUpdateExaminationRequestDto(AddOrUpdateExaminationRequestDto request) throws Exception {
        return studysetServiceThirdParty.addOrUpdateExaminationRequestDto(request);
    }
}
