package source.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import source.constant.ErrorCodeConstant;
import source.dto.QuestionDto;
import source.dto.TestResultDto;
import source.dto.UserDto;
import source.dto.request.*;
import source.dto.response.BaseResponse;
import source.entity.Answer;
import source.entity.Question;
import source.entity.TestResult;
import source.exception.BusinessErrors;
import source.exception.BusinessException;
import source.repository.QuestionRepository;
import source.repository.TestResultRepository;
import source.third_party.course.service.CourseThirdPartyService;
import source.third_party.multimedia.service.MultimediaThirdPartyService;
import source.third_party.user.dto.request.UserGetByIdRequestDto;
import source.third_party.user.dto.request.UserGetListByIdsRequestDto;
import source.third_party.user.service.UserServiceThirdParty;
import source.util.JsonUtil;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuestionBankServiceImpl implements QuestionBankService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private Environment environment;

    @Autowired
    private MultimediaThirdPartyService multimediaThirdPartyService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CourseThirdPartyService courseThirdPartyService;

    @Autowired
    private TestResultRepository testResultRepository;

    @Autowired
    private UserServiceThirdParty userServiceThirdParty;

    private <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
            .stream()
            .map(element -> modelMapper.map(element, targetClass))
            .collect(Collectors.toList());
    }

    @Override
    public BaseResponse createQuestion(QuestionDto request) throws Exception {
        // Thực hiện lưu
        Question questionSave = modelMapper.map(request, Question.class);
        questionSave.setAnswers(mapList(request.getAnswers(), Answer.class));
        questionSave = questionRepository.save(questionSave);

        // Trả kết quả về
        QuestionDto questionDto = modelMapper.map(questionSave, QuestionDto.class);
        return BaseResponse.ofSucceeded(request.getRequestId(), questionDto);
    }

    @Override
    public BaseResponse updateQuestion(QuestionDto request) throws Exception {
        // Kiểm tra question có tồn tại hay không
        Question question = checkQuestionExits(request.getId());

        // Set dữ liệu để update
        Question questionSave = modelMapper.map(request, Question.class);
        questionSave.setId(null);
        questionSave.setAnswers(mapList(request.getAnswers(), Answer.class));
        questionSave.setCreateTime(question.getCreateTime());
        questionSave.setUpdateTime(new Date());

        // Xóa cái cũ
        questionRepository.delete(question);

        // Thực hiện lưu question mới
        questionSave.setId(question.getId());
        questionSave = questionRepository.save(questionSave);

        // Trả về kết quả
        QuestionDto questionResponseDto = modelMapper.map(questionSave, QuestionDto.class);
        return BaseResponse.ofSucceeded(request.getRequestId(), questionResponseDto);
    }

    private Question checkQuestionExits(String questionId) throws Exception {
        // Kiểm tra Question có tồn tại hay không
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        if(!optionalQuestion.isPresent()) {
            throw new BusinessException(BusinessErrors.INVALID_PARAMETERS, environment.getProperty(ErrorCodeConstant.QUESTION_ID_NOT_FOUND_400031));
        }

        return optionalQuestion.get();
    }

    @Override
    public BaseResponse deleteQuestionById(DeleteQuestionByIdRequestDto request) throws Exception {
        // Kiểm tra question có tồn tại hay không
        Question question = checkQuestionExits(request.getQuestionId());

        // Xóa question
        questionRepository.delete(question);

        // Trả về kết quả
        return BaseResponse.ofSucceeded(request.getRequestId(), question);
    }

    @Override
    public BaseResponse getQuestionById(GetQuestionByIdRequestDto request) throws Exception {
        // Kiểm tra question có tồn tại hay không?
        Question question = checkQuestionExits(request.getQuestionId());

        QuestionDto questionDto = modelMapper.map(question, QuestionDto.class);

        // Nếu user tồn tại thì lấy kết quả test ra
        if(request.getUserId() != null) {
            Optional<TestResult> testResultOptional = testResultRepository.findTestResultByUserIdAndQuestionId(
                request.getUserId(), question.getId()
            );
            testResultOptional.ifPresent(testResult -> questionDto.setTestResult(modelMapper.map(testResult, TestResultDto.class)));
        }

        // Trả về kết quả
        return BaseResponse.ofSucceeded(request.getRequestId(), questionDto);
    }

    @Override
    public BaseResponse getAllQuestion(GetAllQuestionDto request) throws Exception {
        // Lấy ra list theo paging and sorting
        PageRequest pageRequest = PageRequest.of(
            request.getPage(),
            request.getSize(),
            Sort.by("updateTime").descending().and(Sort.by("createTime").descending())
        );
        Page<Question> questionPage = questionRepository.findAll(pageRequest);

        // Set thêm số lượng người dùng đã làm
        Page<QuestionDto> quétionDtosPage = questionPage.map(question -> {
            QuestionDto questionDto = modelMapper.map(question, QuestionDto.class);
            questionDto.setUserCount(testResultRepository.countUserByQuestionId(question.getId()));
            // Nếu user tồn tại thì lấy kết quả test ra
            if(request.getUserId() != null) {
                Optional<TestResult> testResultOptional = testResultRepository.findTestResultByUserIdAndQuestionId(
                    request.getUserId(), question.getId()
                );
                testResultOptional.ifPresent(testResult -> questionDto.setTestResult(modelMapper.map(testResult, TestResultDto.class)));
            }
            return questionDto;
        });

        // Trả về kết quả
        return BaseResponse.ofSucceeded(
            request.getRequestId(),
            quétionDtosPage
        );
    }

    @Override
    public BaseResponse getRankQuestion(GetRankQuestionDto request) throws Exception {
        // Kiểm tra studyset có tồn tại hay không
        Question question = checkQuestionExits(request.getQuestionId());

        // Lấy ra xếp hạng của studyset này
        PageRequest pageRequest = PageRequest.of(
            request.getPage(),
            request.getSize(),
            Sort.by("score").descending()
                .and(Sort.by("completionTime").ascending()
                .and(Sort.by("updateTime").ascending()
                .and(Sort.by("createTime").ascending())))
        );
        Page<TestResult> testResultsPage = testResultRepository.findAllByQuestionId(request.getQuestionId(), pageRequest);

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
        return BaseResponse.ofSucceeded(request.getRequestId(), testResultDtosResponse);
    }

    @Override
    public BaseResponse saveTestResult(TestResultDto request) throws Exception {
        // Kiểm tra user có tồn tại không
        UserDto userDto = checkUserExits(request, request.getUserId());

        // Kiểm tra question có tồn tại hay không
        Question question = checkQuestionExits(request.getQuestionId());

        // Thực hiện lưu score
        Optional<TestResult> optionalTestResultDto
            = testResultRepository.findTestResultByUserIdAndQuestionId(request.getUserId(), request.getQuestionId());

        TestResult testResultResponse = null;
        if(!optionalTestResultDto.isPresent()) {
            TestResult testResultSave = modelMapper.map(request, TestResult.class);
            testResultSave.setQuestion(question);
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
        testResultDto.setQuestionId(testResultResponse.getQuestion().getId());
        return BaseResponse.ofSucceeded(request.getRequestId(), testResultDto);
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
}