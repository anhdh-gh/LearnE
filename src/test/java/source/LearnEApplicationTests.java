package source;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import source.entity.Examination;
import source.entity.Studyset;
import source.entity.WordCard;
import source.repository.ExaminationRepository;
import source.repository.StudysetRepository;

import java.util.Arrays;

@SpringBootTest
class LearnEApplicationTests {

    @Autowired
    private StudysetRepository studysetRepository;

    @Autowired
    private ExaminationRepository examinationRepository;

    @Test
    void contextLoads() {

//        studysetRepository.save(Studyset
//            .builder()
//                .title("Title")
//                .description("Description")
//                .wordCards(Arrays.asList(
//                    WordCard
//                        .builder()
//                            .key("Key")
//                            .value("value")
//                            .image("image")
//                        .build()
//                ))
//            .build());

//        examinationRepository.save(Examination
//            .builder()
//            .score(9.5f)
//            .userId("123")
//            .studyset(studysetRepository.findById("e0827983-a517-4eb8-878d-7a6ff5303b30").get())
//            .build());

//        Examination examination = examinationRepository.findExaminationByUserIdAndStudysetId("123", "e0827983-a517-4eb8-878d-7a6ff5303b30").get();
//
//        System.out.println(examination;
    }
}
