package source.dto.response.get_course_detail_for_user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source.entity.Lesson;
import source.entity.enumeration.StatusType;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LessonDto extends Lesson {

    private StatusType status;

    @JsonProperty("lessonExercises")
    private List<LessonExerciseDto> lessonExerciseDtos;
}
