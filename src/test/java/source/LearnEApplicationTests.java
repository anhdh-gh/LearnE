package source;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import source.entity.Question;
import source.repository.QuestionRepository;

import java.lang.reflect.Array;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class LearnEApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    void contextLoads() {
        List<Question> questions = questionRepository.findAll();

        questions.forEach(question -> {
            question.setGroupId(UUID.randomUUID().toString());
            questionRepository.save(question);
        });
    }
}
