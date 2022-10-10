package source.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonQuestionDto extends BaseDto {

    @JsonIgnore
    private String questionId;

    private QuestionDto question;

    private float score;
}
