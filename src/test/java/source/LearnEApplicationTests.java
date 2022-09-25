package source;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import source.entity.*;
import source.entity.enumeration.StatusType;
import source.repository.*;

@SpringBootTest
class LearnEApplicationTests {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LessonStatusRepository lessonStatusRepository;

    @Autowired
    private LessonExerciseStatusRepository lessonExerciseStatusRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private LessonExerciseRepository lessonExerciseRepository;

    @Test
    void contextLoads() {
//        courseRepository.save(Course
//            .builder()
//            .name("Name course")
//            .chapters(Arrays.asList(
//                Chapter
//                .builder()
//                .name("Chapter 1")
//                .lessons(Arrays.asList(
//                    Lesson
//                    .builder()
//                    .name("Lesson name")
//                    .video("Lesson video")
//                    .lessonExercises(Arrays.asList(
//                        LessonExercise
//                        .builder()
//                        .questionId("123456789") // Call API để kiếm tra xem question id có tồn tại không
//                        .build()
//                    ))
//                    .build()
//                ))
//                .build()
//            ))
//            .extraDataList(Arrays.asList(
//                ExtraData
//                .builder()
//                .extraDataKey(ExtraDataKey.REQUEST)
//                .extraDataValue("value")
//                .build()
//            ))
//            .build()
//        );

//        courseRepository.delete(Course.builder().id("4b542ccc-b488-4ca7-a0a3-1b548ec6faec").build());

//        Lesson lesson = lessonRepository.findById("3d8fc9aa-9052-44cd-a620-09a98b3c7e07").get();
//        lessonStatusRepository.save(
//            LessonStatus
//            .builder()
//            .userId("userid")
//            .status(StatusType.FINISHED)
//            .lesson(lesson)
//            .build()
//        );

//        lessonStatusRepository.delete(LessonStatus.builder().id("cf5d79fb-d3ab-4363-8c75-00cc078015fe").build());

//        LessonExercise lessonExercise = lessonExerciseRepository.findById("07ebc233-0249-4efc-bb5a-e80ecb3df77c").get();
//        lessonExerciseStatusRepository.save(
//            LessonExerciseStatus
//            .builder()
//            .userId("userid")
//            .status(StatusType.FINISHED)
//            .lessonExercise(lessonExercise)
//            .build()
//        );

//        lessonExerciseStatusRepository.delete(LessonExerciseStatus.builder().id("35101974-9bf9-4ded-b508-e7ecdd4fa4ee").build());
    }
}
