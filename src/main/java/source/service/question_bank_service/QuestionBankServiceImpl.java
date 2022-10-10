package source.service.question_bank_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.dto.request.CreateQuestionRequestDto;
import source.dto.request.GetQuestionByQuestionIdRequestDto;
import source.dto.request.QuestionGetAllRequestDto;
import source.dto.response.BaseResponse;
import source.third_party.question.service.QuestionBankServiceThirdParty;

@Service
public class QuestionBankServiceImpl implements QuestionBankService {

    @Autowired
    private QuestionBankServiceThirdParty questionServiceThirdParty;

    @Override
    public BaseResponse getQuestionByQuestionId(GetQuestionByQuestionIdRequestDto request) throws Exception {
        return questionServiceThirdParty.getQuestionByQuestionId(request);
    }

    @Override
    public BaseResponse createQuestion(CreateQuestionRequestDto request) throws Exception {
        return questionServiceThirdParty.createQuestion(request);
    }

    @Override
    public BaseResponse getAllQuestion(QuestionGetAllRequestDto request) throws Exception {
        return questionServiceThirdParty.getAllQuestion(request);
    }
}
