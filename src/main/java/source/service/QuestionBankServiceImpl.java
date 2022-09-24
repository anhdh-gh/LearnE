package source.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.entity.Question;
import source.repository.QuestionRepository;

@Service
public class QuestionBankServiceImpl implements QuestionBankService {

    @Autowired
    private QuestionRepository questionRepository;
}