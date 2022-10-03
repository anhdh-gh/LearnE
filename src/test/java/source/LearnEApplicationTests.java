package source;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import source.dto.request.CreateCourseRequestDto;
import source.entity.*;
import source.service.CourseService;

import java.util.Arrays;

@SpringBootTest
class LearnEApplicationTests {

    @Autowired
    private CourseService courseService;

    @Test
    void contextLoads() throws Exception {
        courseService.createCourse(CreateCourseRequestDto.builder()
            .name("Làm việc với Terminal & Ubuntu")
                .chapters(Arrays.asList(
                Chapter
                .builder()
                .name("Chapter 1")
                .lessons(Arrays.asList(
                    Lesson
                    .builder()
                    .name("Lesson name")
                    .video("https://youtu.be/2h6uajmjSk8")
                    .duration("02:15")
                    .lessonExercises(Arrays.asList(
                        LessonExercise
                        .builder()
                        .name("Exercise 1")
                        .lessonQuestions(Arrays.asList(LessonQuestion.builder()
                            .questionId("123456")
                            .build()))
                        .build()
                    ))
                    .build()
                ))
                .build()
            ))
            .targets(Arrays.asList(Target.builder()
                .text("target 1")
                .build()))
            .requests(Arrays.asList(Request.builder()
                .text("request 1")
                .build()))
            .build());
    }
}
