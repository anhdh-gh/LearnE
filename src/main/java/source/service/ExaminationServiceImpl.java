package source.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import source.constant.ErrorCodeConstant;
import source.dto.request.AddOrUpdateExaminationRequestDto;
import source.dto.response.AddOrUpdateExaminationResponseDto;
import source.dto.response.BaseResponse;
import source.entity.Examination;
import source.entity.Studyset;
import source.exception.BusinessErrors;
import source.repository.ExaminationRepository;
import source.repository.StudysetRepository;
import source.third_party.user.dto.request.UserGetByIdRequestDto;
import source.third_party.user.service.UserServiceThirdParty;

import java.util.Optional;

@Service
public class ExaminationServiceImpl implements ExaminationService {

    @Autowired
    private ExaminationRepository examinationRepository;

    @Autowired
    private UserServiceThirdParty userServiceThirdParty;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Environment environment;

    @Autowired
    private StudysetRepository studysetRepository;

    @Override
    public BaseResponse addOrUpdateExaminationRequestDto(AddOrUpdateExaminationRequestDto request) throws Exception {
        // Kiểm tra xem UserId có tồn tại hay không
        BaseResponse baseResponse = userServiceThirdParty.getUserById(UserGetByIdRequestDto
            .builder()
            .requestId(request.getRequestId())
            .id(request.getUserId())
            .build()
        );
        if(!baseResponse.getMeta().getCode().equals(BaseResponse.OK_CODE)) {
            return BaseResponse.ofFailed(request.getRequestId(), BusinessErrors.INVALID_PARAMETERS, environment.getProperty(ErrorCodeConstant.USERID_IS_NOT_EXISTS_400011));
        }

        // Kiểm tra studyset có tồn tại hay không
        Optional<Studyset> optionalStudyset = studysetRepository.findById(request.getStudysetId());
        if(!optionalStudyset.isPresent()) {
            return BaseResponse.ofFailed(request.getRequestId(), BusinessErrors.INVALID_PARAMETERS, environment.getProperty(ErrorCodeConstant.STUDYSET_NOT_FOUND_400032));
        }

        Optional<Examination> examinationOptional
            = examinationRepository.findExaminationByUserIdAndStudysetId(request.getUserId(), request.getStudysetId());

        if(!examinationOptional.isPresent()) {
            Examination examinationSave = Examination
                .builder()
                .userId(request.getUserId())
                .studyset(optionalStudyset.get())
                .score(request.getScore())
                .build();

            examinationRepository.save(examinationSave);
        } else {
            Examination examination = examinationOptional.get();
            examination.setScore(request.getScore());
            examinationRepository.save(examination);
        }

        return BaseResponse.ofSucceeded(request.getRequestId(), modelMapper.map(request, AddOrUpdateExaminationResponseDto.class));
    }
}