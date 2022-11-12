package source.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import source.constant.ErrorCodeConstant;
import source.dto.request.*;
import source.dto.response.BaseResponse;
import source.entity.Answer;
import source.entity.Question;
import source.entity.enumeration.QuestionType;
import source.exception.BusinessErrors;
import source.exception.BusinessException;
import source.repository.QuestionRepository;
import source.third_party.multimedia.dto.request.QuestionCheckExistRequestDto;
import source.third_party.multimedia.dto.request.QuestionDeleteByGroupIdRequestDto;
import source.third_party.multimedia.dto.request.QuestionDetail;
import source.third_party.multimedia.service.MultimediaThirdPartyService;

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

    @Override
    public BaseResponse getQuestionByQuestionId(GetQuestionByQuestionIdRequestDto request) throws Exception {
        Optional<Question> questionOptional = questionRepository.findById(request.getQuestionId());

        if(questionOptional.isPresent()) {
            return BaseResponse.ofSucceeded(request.getRequestId(), questionOptional.get());
        } else {
            int errorCode = Integer.parseInt(ErrorCodeConstant.QUESTION_ID_NOT_FOUND_400031);
            throw new BusinessException(errorCode, environment.getProperty(String.valueOf(errorCode)), HttpStatus.BAD_REQUEST);
        }
    }

    private Question saveQuestion(CreateQuestionRequestDto request) throws Exception {
        return questionRepository.save(Question
            .builder()
            .id(request.getId())
            .questionType(QuestionType.valueOf(request.getQuestionType()))
            .text(request.getText())
            .image(request.getImage())
            .audio(request.getAudio())
            .groupId(request.getGroupId())
            .header(request.getHeader())
            .answers(request.getAnswers()
                .stream()
                .map(answerRequestDto -> Answer
                    .builder()
                    .text(answerRequestDto.getText())
                    .isCorrect(answerRequestDto.isCorrect())
                    .build()
                )
                .collect(Collectors.toList()))
            .build()
        );
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public BaseResponse createQuestionsList(CreateListQuestionsRequestDto request) throws Exception {
        List<Question> questions = new ArrayList<>();

        // Kiểm tra id, image, audio, groupId có tồn tại không
        if(request.getQuestions().stream().anyMatch(question -> question.getImage() != null || question.getAudio() != null)) {
            QuestionCheckExistRequestDto questionCheckExistRequest =
                modelMapper.map(request, QuestionCheckExistRequestDto.class);
            questionCheckExistRequest.setGroupId(request.getGroupId());
            questionCheckExistRequest.setQuestions(mapList(request.getQuestions(), QuestionDetail.class));
            BaseResponse responseCheckExist = multimediaThirdPartyService.checkQuestionExist(questionCheckExistRequest);
            if(!Objects.equals(responseCheckExist.getMeta().getCode(), BaseResponse.OK_CODE)) {
                return responseCheckExist;
            }
        }

        // Thực hiện save
        for(CreateQuestionRequestDto questionRequestDto : request.getQuestions()) {
            questionRequestDto.setGroupId(request.getGroupId());
            Question questionSave = saveQuestion(questionRequestDto);
            questions.add(questionSave);
        }
        return BaseResponse.ofSucceeded(request.getRequestId(), questions);
    }

    @Override
    public BaseResponse updateQuestionsList(UpdateListQuestionsRequestDto request) throws Exception {
        // Kiểm tra xem có tồn tai không
        List<Question> questions = getQuestionByQuestionIds(
            request.getQuestions().stream()
            .map(UpdateQuestionRequestDto::getId)
            .collect(Collectors.toSet())
        );

        // Kiểm tra các id có cùng một group không
        List<String> groupIds = questions.stream().map(Question::getGroupId).distinct().collect(Collectors.toList());
        if(groupIds.size() != 1) {
            return BaseResponse.ofFailed(
                request.getRequestId(),
                BusinessErrors.INVALID_PARAMETERS
            );
        }

        // Nếu tồn tại thì xóa cái cũ
        QuestionDeleteByGroupIdRequestDto questionDeleteByGroupIdRequest
            = modelMapper.map(request, QuestionDeleteByGroupIdRequestDto.class);
        questionDeleteByGroupIdRequest.setGroupId(groupIds.get(0));
        multimediaThirdPartyService.deleteQuestionByGroupId(questionDeleteByGroupIdRequest);

        // Thực hiện tạo cái mới
        CreateListQuestionsRequestDto createListQuestionsRequest
            = modelMapper.map(request, CreateListQuestionsRequestDto.class);
        createListQuestionsRequest.setQuestions(mapList(request.getQuestions(), CreateQuestionRequestDto.class));
        return createQuestionsList(createListQuestionsRequest);
    }

    @Override
    public BaseResponse getAllQuestion(QuestionGetAllRequestDto request) throws Exception {
        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize());

        Page<Question> questions = questionRepository.findAll(pageRequest);

        return BaseResponse.ofSucceeded(
            request.getRequestId(),
            questions
        );
    }

    @Override
    public BaseResponse getQuestionByQuestionIds(QuestionGetByIdsRequestDto request) throws Exception {
        List<Question> questions = getQuestionByQuestionIds(request.getQuestionIds());
        return BaseResponse.ofSucceeded(request.getRequestId(), questions);
    }

    private List<Question> getQuestionByQuestionIds(Set<String> questionIds) throws Exception {
        List<Question> questions = questionRepository.findByIdIn(questionIds);
        if(questions.size() != questionIds.size()) {
            int errorCode = Integer.parseInt(ErrorCodeConstant.QUESTION_ID_NOT_FOUND_400031);
            throw new BusinessException(errorCode, environment.getProperty(String.valueOf(errorCode)), HttpStatus.BAD_REQUEST);
        }

        return questions;
    }

    private <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
            .stream()
            .map(element -> modelMapper.map(element, targetClass))
            .collect(Collectors.toList());
    }
}