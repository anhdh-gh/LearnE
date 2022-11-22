package source;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import source.entity.Answer;
import source.entity.Question;
import source.repository.QuestionRepository;

import java.util.Collections;

@SpringBootTest
class LearnEApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    void contextLoads() {
        questionRepository.save(Question
            .builder()
            .text("Text")
            .pdf("Pdf")
            .answers(Collections.singletonList(
                Answer
                .builder()
                .text("Text")
                .build()))
            .build());
    }
}
