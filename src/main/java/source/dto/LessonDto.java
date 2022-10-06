package source.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.entity.enumeration.StatusType;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonDto extends BaseDto{

    private String name;

    private String duration;

    private String description;

    private String video;

    private StatusType status = StatusType.UNFINISHED;

    private List<LessonExerciseDto> lessonExercises;
}
