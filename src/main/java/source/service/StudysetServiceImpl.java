package source.service;

import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import source.constant.ErrorCodeConstant;
import source.dto.StudysetDto;
import source.dto.UserDto;
import source.dto.request.*;
import source.dto.response.BaseResponse;
import source.entity.Examination;
import source.entity.Studyset;
import source.entity.WordCard;
import source.exception.BusinessErrors;
import source.exception.BusinessException;
import source.repository.ExaminationRepository;
import source.repository.StudysetRepository;
import source.third_party.user.dto.request.UserGetByIdRequestDto;
import source.third_party.user.dto.request.UserGetListByIdsRequestDto;
import source.third_party.user.service.UserServiceThirdParty;
import source.util.JsonUtil;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudysetServiceImpl implements StudysetService {

    @Autowired
    private StudysetRepository studysetRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Environment environment;

    @Autowired
    private ExaminationRepository examinationRepository;

    @Autowired
    private UserServiceThirdParty userServiceThirdParty;

    @Override
    public BaseResponse createStudyset(StudysetDto request) throws Exception {
        // Kiểm tra user có tồn tại không
        UserDto userDto = checkUserExits(request, request.getOwnerUserId());

        // Thực hiện lưu
        Studyset studysetSave = modelMapper.map(request, Studyset.class);
        studysetSave.setWordCards(mapList(request.getWordCards(), WordCard.class));
        studysetSave = studysetRepository.save(studysetSave);

        // Trả kết quả về
        StudysetDto studysetDto = modelMapper.map(studysetSave, StudysetDto.class);
        studysetDto.setOwnerUser(userDto);
        return BaseResponse.ofSucceeded(request.getRequestId(), studysetDto);
    }

    @Override
    public BaseResponse updateStudyset(StudysetDto request) throws Exception {
        // Kiểm tra user có tồn tại không
        UserDto userDto = checkUserExits(request, request.getOwnerUserId());

        // Kiểm tra studyset có tồn tại hay không
        Studyset studyset = checkStudysetExits(request.getId());

        // Kiểm tra xem chủ sỡ hữu studyset có đúng không
        checkOwnerUserIdValid(request.getOwnerUserId(), studyset);

        // Xóa cái cũ
        studysetRepository.delete(studyset);

        // Thực hiện lưu studyset mới
        Studyset studysetSave = modelMapper.map(request, Studyset.class);
        studysetSave.setWordCards(mapList(request.getWordCards(), WordCard.class));
        studysetSave.setCreateTime(studyset.getCreateTime());
        studysetSave = studysetRepository.save((studysetSave));

        // Trả về kết quả
        StudysetDto studysetResponseDto = modelMapper.map(studysetSave, StudysetDto.class);
        studysetResponseDto.setOwnerUser(userDto);
        return BaseResponse.ofSucceeded(request.getRequestId(), studysetResponseDto);
    }

    @Override
    public BaseResponse deleteStudyset(DeleteStudysetByIdRequestDto request) throws Exception {
        // Kiểm tra studyset có tồn tại hay không
        Studyset studyset = checkStudysetExits(request.getStudysetId());

        // Kiểm tra xem chủ sỡ hữu studyset có đúng không
        checkOwnerUserIdValid(request.getOwnerUserId(), studyset);

        // Xóa studyset
        studysetRepository.delete(studyset);

        // Trả về kết quả
        return BaseResponse.ofSucceeded(request.getRequestId(), studyset);
    }

    @Override
    public BaseResponse getStudysetById(GetStudysetByIdRequestDto request) throws Exception {
        Studyset studyset = checkStudysetExits(request.getStudysetId());

        return BaseResponse.ofSucceeded(request.getRequestId(), studyset);
    }

    @Override
    public BaseResponse saveStudysetScore(SaveStudysetScore request) throws Exception {
        // Kiểm tra user có tồn tại không
        UserDto userDto = checkUserExits(request, request.getUserId());

        // Kiểm tra studyset có tồn tại hay không
        Studyset studyset = checkStudysetExits(request.getStudysetId());

        // Thực hiện lưu score
        Optional<Examination> examinationOptional
            = examinationRepository.findExaminationByUserIdAndStudysetId(request.getUserId(), request.getStudysetId());

        if(!examinationOptional.isPresent()) {
            Examination examinationSave = Examination
                .builder()
                .userId(request.getUserId())
                .studyset(studyset)
                .score(request.getScore())
                .build();

            examinationRepository.save(examinationSave);
        } else {
            Examination examination = examinationOptional.get();
            examination.setScore(request.getScore());
            examinationRepository.save(examination);
        }

        // Trả kết quả về
        return BaseResponse.ofSucceeded(request.getRequestId(), request);
    }

    @Override
    public BaseResponse getAllStudysetByOwnerUserId(GetAllStudysetByOwnerUserIdRequestDto request) throws Exception {
        // Kiểm tra user có tồn tại không
        UserDto userDto = checkUserExits(request, request.getOwnerUserId());

        // Lấy ra list theo paging and sorting
        PageRequest pageRequest = PageRequest.of(
            request.getPage(),
            request.getSize(),
            Sort.by("updateTime").descending().and(Sort.by("createTime").descending())
        );
        Page<Studyset> studysetsPage = studysetRepository.findAllByOwnerUserId(request.getOwnerUserId(), pageRequest);

        // Set thêm owner User để trả về
        Page<StudysetDto> studysetDtosPage = studysetsPage.map(studyset -> {
            StudysetDto studysetDto = modelMapper.map(studyset, StudysetDto.class);
            studysetDto.setOwnerUser(userDto);
            return studysetDto;
        });

        // Trả về kết quả
        return BaseResponse.ofSucceeded(
            request.getRequestId(),
            studysetDtosPage
        );
    }

    @Override
    public BaseResponse getAllStudyset(GetAllStudysetRequestDto request) throws Exception {
        // Lấy ra list theo paging and sorting
        PageRequest pageRequest = PageRequest.of(
            request.getPage(),
            request.getSize(),
            Sort.by("updateTime").descending().and(Sort.by("createTime").descending())
        );
        Page<Studyset> studysetsPage = studysetRepository.findAll(pageRequest);

        // Lấy ra một list user theo userIds
        Set<String> userIds = studysetsPage.toList().stream().map(Studyset::getOwnerUserId).collect(Collectors.toSet());
        Map<String, UserDto> userDtosMap = getListUserByUserIds(request, userIds).stream().collect(Collectors.toMap(UserDto::getId, userDto -> userDto));

        // Set thêm owner User để trả về
        Page<StudysetDto> studysetDtosPage = studysetsPage.map(studyset -> {
            StudysetDto studysetDto = modelMapper.map(studyset, StudysetDto.class);
            studysetDto.setOwnerUser(userDtosMap.get(studysetDto.getOwnerUserId()));
            return studysetDto;
        });

        // Trả về kết quả
        return BaseResponse.ofSucceeded(
            request.getRequestId(),
            studysetDtosPage
        );
    }

    private <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
            .stream()
            .map(element -> modelMapper.map(element, targetClass))
            .collect(Collectors.toList());
    }

    private UserDto checkUserExits(BasicRequest request, String userId) throws Exception {
        // Kiểm tra xem UserId có tồn tại hay không
        BaseResponse baseResponse = userServiceThirdParty.getUserById(UserGetByIdRequestDto
            .builder()
            .requestId(request.getRequestId())
            .id(userId)
            .build()
        );
        if(!baseResponse.getMeta().getCode().equals(BaseResponse.OK_CODE)) {
            throw new BusinessException(BusinessErrors.INVALID_PARAMETERS, environment.getProperty(ErrorCodeConstant.USERID_IS_NOT_EXISTS_400011));
        }

        return JsonUtil.getGenericObject(baseResponse.getData(), UserDto.class);
    }

    private Studyset checkStudysetExits(String studysetId) throws Exception {
        // Kiểm tra studyset có tồn tại hay không
        Optional<Studyset> optionalStudyset = studysetRepository.findById(studysetId);
        if(!optionalStudyset.isPresent()) {
            throw new BusinessException(BusinessErrors.INVALID_PARAMETERS, environment.getProperty(ErrorCodeConstant.STUDYSET_NOT_FOUND_400032));
        }

        return optionalStudyset.get();
    }

    private List<UserDto> getListUserByUserIds(BasicRequest request, Set<String> userIds) throws Exception {
        // Kiểm tra xem UserId có tồn tại hay không
        BaseResponse baseResponse = userServiceThirdParty.getUserByUserIds(UserGetListByIdsRequestDto
            .builder()
            .requestId(request.getRequestId())
            .ids(userIds)
            .build()
        );
        if(!baseResponse.getMeta().getCode().equals(BaseResponse.OK_CODE)) {
            throw new BusinessException(BusinessErrors.INVALID_PARAMETERS, environment.getProperty(ErrorCodeConstant.USERID_IS_NOT_EXISTS_400011));
        }

        List userlistRaw = JsonUtil.getGenericObject(baseResponse.getData(), List.class);
        List<UserDto> userDtos = new ArrayList<>();
        userlistRaw.forEach(userRaw -> {
            UserDto userDto = JsonUtil.getGenericObject(userRaw, UserDto.class);
            userDtos.add(userDto);
        });

        return mapList(userDtos, UserDto.class);
    }

    public void checkOwnerUserIdValid(String ownerUserId, Studyset studyset) throws Exception {
        // Kiểm tra xem chủ sỡ hữu studyset có đúng không
        if(!ownerUserId.equals(studyset.getOwnerUserId())) {
            throw new BusinessException(BusinessErrors.INVALID_PARAMETERS, environment.getProperty(ErrorCodeConstant.OWNER_USER_ID_IN_STUDYSET_INVALID_400033));
        }
    }
}
