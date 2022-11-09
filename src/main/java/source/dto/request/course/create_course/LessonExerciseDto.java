package source.dto.request.course.create_course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LessonExerciseDto {

    private String name;

    private String description;

    private List<LessonQuestionDto> lessonQuestions;
}
