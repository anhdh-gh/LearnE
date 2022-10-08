package source.dto.request.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.dto.BaseDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonQuestionDto extends BaseDto {

    private String questionId;

    private QuestionDto question;

    private float score;
}
