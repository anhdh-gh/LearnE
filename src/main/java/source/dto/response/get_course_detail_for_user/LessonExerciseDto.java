package source.dto.response.get_course_detail_for_user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source.entity.LessonExercise;
import source.entity.enumeration.Provider;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LessonExerciseDto extends LessonExercise {

    private String referenceId;

    private Provider provider;

    private Object question;
}
