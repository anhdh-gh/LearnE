package source.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonDto {

    private String name;

    private String description;

    private String video;

    private List<LessonExerciseDto> lessonExercises;
}
