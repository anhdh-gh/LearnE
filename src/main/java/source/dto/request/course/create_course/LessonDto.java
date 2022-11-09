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
public class LessonDto {

    private String name;

    private String duration;

    private String description;

    private String video;

    private List<LessonExerciseDto> lessonExercises;
}
