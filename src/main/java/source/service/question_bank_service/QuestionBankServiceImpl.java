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
    public BaseResponse getQuestionByQuestionId(GetQuestionByQuestionIdRequestDto request) throws Exception {
        return questionBankServiceThirdParty.getQuestionByQuestionId(request);
    }

    @Override
    public BaseResponse createQuestionsList(CreateListQuestionsRequestDto request) throws Exception {
        return questionBankServiceThirdParty.createQuestionsList(request);
    }

    @Override
    public BaseResponse getAllQuestion(QuestionGetAllRequestDto request) throws Exception {
        return questionBankServiceThirdParty.getAllQuestion(request);
    }

    @Override
    public BaseResponse getQuestionByQuestionIds(QuestionGetByIdsRequestDto request) throws Exception {
        return questionBankServiceThirdParty.getQuestionByQuestionIds(request);
    }

    @Override
    public BaseResponse deleteQuestionsListByGroupId(DeleteListQuestionsByGroupIdRequestDto request) throws Exception {
        return questionBankServiceThirdParty.deleteQuestionsListByGroupId(request);
    }
}
