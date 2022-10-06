package source.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.entity.enumeration.StatusType;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonExerciseDto extends BaseDto{

    private String name;

    private String description;

    private List<LessonQuestionDto> lessonQuestions;

    private StatusType status = StatusType.UNFINISHED;
}