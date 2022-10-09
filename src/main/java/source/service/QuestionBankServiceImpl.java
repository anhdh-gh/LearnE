package source.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import source.constant.ErrorCodeConstant;
import source.dto.request.*;
import source.dto.response.BaseResponse;
import source.entity.Answer;
import source.entity.Question;
import source.entity.enumeration.QuestionType;
import source.exception.BusinessException;
import source.repository.QuestionRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class QuestionBankServiceImpl implements QuestionBankService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private Environment environment;
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

    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public BaseResponse createQuestion(CreateQuestionRequestDto request) throws Exception {
        Question questionSave = saveQuestion(request);
        return BaseResponse.ofSucceeded(request.getRequestId(), questionSave);
    }

    private Question saveQuestion(CreateQuestionRequestDto request) throws Exception {
        return questionRepository.save(Question
            .builder()
            .id(request.getId())
            .questionType(QuestionType.valueOf(request.getQuestionType()))
            .text(request.getText())
            .image(request.getImage())
            .audio(request.getAudio())
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
        Set<String> ids = new HashSet<>();
        for(CreateQuestionRequestDto questionRequestDto : request.getQuestions()) {
            Question questionSave = saveQuestion(questionRequestDto);
            ids.add(questionSave.getId());
        }
        return getQuestionByQuestionIds(QuestionGetByIdsRequestDto.builder().questionIds(ids).build());
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
        List<Question> questions = questionRepository.findByIdIn(request.getQuestionIds());
        if(questions.size() != request.getQuestionIds().size()) {
            int errorCode = Integer.parseInt(ErrorCodeConstant.QUESTION_ID_NOT_FOUND_400031);
            throw new BusinessException(errorCode, environment.getProperty(String.valueOf(errorCode)), HttpStatus.BAD_REQUEST);
        }
        return BaseResponse.ofSucceeded(request.getRequestId(), questions);
    }
}