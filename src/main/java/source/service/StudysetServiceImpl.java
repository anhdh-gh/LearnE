package source.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import source.constant.ErrorCodeConstant;
import source.dto.request.CreateStudysetRequestDto;
import source.dto.request.GetStudysetByIdRequestDto;
import source.dto.response.BaseResponse;
import source.entity.Studyset;
import source.exception.BusinessErrors;
import source.repository.StudysetRepository;

import java.util.Optional;

@Service
public class StudysetServiceImpl implements StudysetService {

    @Autowired
    private StudysetRepository studysetRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Environment environment;

    @Override
    public BaseResponse createStudyset(CreateStudysetRequestDto request) throws Exception {
        Studyset studysetSave = modelMapper.map(request, Studyset.class);
        studysetRepository.save(studysetSave);
        return BaseResponse.ofSucceeded(request.getRequestId(), studysetSave);
    }

    @Override
    public BaseResponse getStudysetById(GetStudysetByIdRequestDto request) throws Exception {
        Optional<Studyset> optionalStudyset = studysetRepository.findById(request.getStudysetId());
        if(!optionalStudyset.isPresent()) {
            return BaseResponse.ofFailed(request.getRequestId(), BusinessErrors.INVALID_PARAMETERS, environment.getProperty(ErrorCodeConstant.STUDYSET_NOT_FOUND_400032));
        }

        return BaseResponse.ofSucceeded(request.getRequestId(), optionalStudyset.get());
    }
}
