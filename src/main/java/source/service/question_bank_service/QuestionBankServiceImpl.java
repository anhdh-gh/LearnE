package source.service.question_bank_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.dto.request.questionBank.*;
import source.dto.response.BaseResponse;
import source.third_party.question.service.QuestionBankServiceThirdParty;

@Service
public class QuestionBankServiceImpl implements QuestionBankService {

    @Autowired
    private QuestionBankServiceThirdParty questionBankServiceThirdParty;


    @Override
    public BaseResponse createQuestion(QuestionDto request) throws Exception {
        return questionBankServiceThirdParty.createQuestion(request);
    }

    @Override
    public BaseResponse updateQuestion(QuestionDto request) throws Exception {
        return questionBankServiceThirdParty.updateQuestion(request);
    }

    @Override
    public BaseResponse deleteQuestionById(DeleteQuestionByIdRequestDto request) throws Exception {
        return questionBankServiceThirdParty.deleteQuestionById(request);
    }

    @Override
    public BaseResponse getQuestionById(GetQuestionByIdRequestDto request) throws Exception {
        return questionBankServiceThirdParty.getQuestionById(request);
    }

    @Override
    public BaseResponse getAllQuestion(GetAllQuestionDto request) throws Exception {
        return questionBankServiceThirdParty.getAllQuestion(request);
    }

    @Override
    public BaseResponse getRankQuestion(GetRankQuestionDto request) throws Exception {
        return questionBankServiceThirdParty.getRankQuestion(request);
    }

    @Override
    public BaseResponse saveTestResult(TestResultDto request) throws Exception {
        return questionBankServiceThirdParty.saveTestResult(request);
    }
}
