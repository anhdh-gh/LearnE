package source.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.dto.request.CreateQuestionRequestDto;
import source.dto.request.GetQuestionByQuestionIdRequestDto;
import source.dto.response.BaseResponse;
import source.entity.Answer;
import source.entity.Question;
import source.entity.enumeration.QuestionType;
import source.exception.BusinessErrors;
import source.repository.QuestionRepository;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionBankServiceImpl implements QuestionBankService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public BaseResponse getQuestionByQuestionId(GetQuestionByQuestionIdRequestDto request) throws Exception {
        Optional<Question> questionOptional = questionRepository.findById(request.getQuestionId());
        if(questionOptional.isPresent()) {
            return BaseResponse.ofSucceeded(request.getRequestId(), questionOptional.get());
        }

        return BaseResponse.ofFailed(request.getRequestId(), BusinessErrors.INVALID_PARAMETERS);
    }

    @Override
    public BaseResponse createQuestion(CreateQuestionRequestDto request) throws Exception {
        Question questionSave = questionRepository.save(Question
            .builder()
            .questionType(QuestionType.valueOf(request.getQuestionType()))
            .text(request.getText())
            .image(request.getImage())
            .audio(request.getAudio())
            .answers(request.getAnswers()
                .stream()
                .map(answerRequestDto -> Answer
                    .builder()
                    .text(answerRequestDto.getText())
                    .build()
                )
                .collect(Collectors.toList()))
            .build()
        );

        return BaseResponse.ofSucceeded(request.getRequestId(), questionSave);
    }
}