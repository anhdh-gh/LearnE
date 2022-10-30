package source.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import source.constant.ErrorCodeConstant;
import source.dto.StudysetDto;
import source.dto.TestResultDto;
import source.dto.UserDto;
import source.dto.request.*;
import source.dto.response.BaseResponse;
import source.entity.Studyset;
import source.entity.TestResult;
import source.entity.WordCard;
import source.exception.BusinessErrors;
import source.exception.BusinessException;
import source.repository.StudysetRepository;
import source.repository.TestResultRepository;
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
    private TestResultRepository testResultRepository;

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

        // Set dữ liệu để update
        Studyset studysetSave = modelMapper.map(request, Studyset.class);
        studysetSave.setId(null);
        studysetSave.setWordCards(mapList(request.getWordCards(), WordCard.class));
        studysetSave.setCreateTime(studyset.getCreateTime());
        studysetSave.setUpdateTime(new Date());

        // Xóa cái cũ
        studysetRepository.delete(studyset);

        // Thực hiện lưu studyset mới
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
        // Kiểm tra studyset có tồn tại hay không?
        Studyset studyset = checkStudysetExits(request.getStudysetId());

        // Lấy ra OwnerUser
        UserDto userDto = checkUserExits(request, studyset.getOwnerUserId());

        // Trả về kết quả
        StudysetDto studysetDto = modelMapper.map(studyset, StudysetDto.class);
        studysetDto.setOwnerUser(userDto);
        return BaseResponse.ofSucceeded(request.getRequestId(), studysetDto);
    }

    @Override
    public BaseResponse saveTestResult(TestResultDto request) throws Exception {
        // Kiểm tra user có tồn tại không
        UserDto userDto = checkUserExits(request, request.getUserId());

        // Kiểm tra studyset có tồn tại hay không
        Studyset studyset = checkStudysetExits(request.getStudysetId());

        // Thực hiện lưu score
        Optional<TestResult> optionalTestResultDto
            = testResultRepository.findTestResultByUserIdAndStudysetId(request.getUserId(), request.getStudysetId());

        TestResult testResultResponse = null;
        if(!optionalTestResultDto.isPresent()) {
            TestResult testResultSave = modelMapper.map(request, TestResult.class);
            testResultSave.setStudyset(studyset);
            testResultResponse = testResultRepository.save(testResultSave);
        } else {
            TestResult testResultUpdate = optionalTestResultDto.get();
            testResultUpdate.setScore(request.getScore());
            testResultUpdate.setCompletionTime(request.getCompletionTime());
            testResultUpdate.setUpdateTime(new Date());
            testResultResponse = testResultRepository.save(testResultUpdate);
        }

        // Trả kết quả về
        TestResultDto testResultDto = modelMapper.map(testResultResponse, TestResultDto.class);
        testResultDto.setStudysetId(testResultResponse.getStudyset().getId());
        return BaseResponse.ofSucceeded(request.getRequestId(), testResultDto);
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

    @Override
    public BaseResponse getRankStudyset(GetRankStudysetRequestDto request) throws Exception {
        // Kiểm tra studyset có tồn tại hay không
        Studyset studyset = checkStudysetExits(request.getStudysetId());

        // Lấy ra xếp hạng của studyset này
        PageRequest pageRequest = PageRequest.of(
            request.getPage(),
            request.getSize(),
            Sort.by("score").descending()
                .and(Sort.by("completionTime").ascending()
                .and(Sort.by("updateTime").ascending()
                .and(Sort.by("createTime").ascending())))
        );
        Page<TestResult> testResultsPage = testResultRepository.findAllByStudysetId(request.getStudysetId(), pageRequest);

        // Lấy ra một list user theo userIds
        Set<String> userIds = testResultsPage.toList().stream().map(TestResult::getUserId).collect(Collectors.toSet());
        Map<String, UserDto> userDtosMap = getListUserByUserIds(request, userIds).stream().collect(Collectors.toMap(UserDto::getId, userDto -> userDto));

        // Tạo response trả về
        Page<TestResultDto> testResultDtosResponse = testResultsPage.map(testResult -> {
            TestResultDto testResultDto = modelMapper.map(testResult, TestResultDto.class);
            testResultDto.setUser(userDtosMap.get(testResult.getUserId()));
            return testResultDto;
        });

        // Trả về kết quả
        return BaseResponse.ofSucceeded(request.getStudysetId(), testResultDtosResponse);
    }

    @Override
    public BaseResponse checkOwnerStudysetValid(CheckOwnerStudysetValidRequestDto request) throws Exception {
        // Kiểm tra studyset có tồn tại hay không
        Studyset studyset = checkStudysetExits(request.getStudysetId());

        // Kiểm tra xem chủ sỡ hữu studyset có đúng không
        checkOwnerUserIdValid(request.getOwnerUserId(), studyset);

        // Kiểm tra user tồn tại và lấy ra OwnerUser
        UserDto userDto = checkUserExits(request, studyset.getOwnerUserId());

        // Trả về kết quả
        StudysetDto studysetDto = modelMapper.map(studyset, StudysetDto.class);
        studysetDto.setOwnerUser(userDto);
        return BaseResponse.ofSucceeded(request.getRequestId(), studysetDto);
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
