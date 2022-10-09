package source.dto.request.create_course;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source.dto.request.BasicRequest;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class LessonQuestionDto extends BasicRequest {

    @JsonProperty("id")
    private String questionId;

    private QuestionType questionType;

    private String text;

    private String image;

    private String audio;

    private List<AnswerDto> answers;

    private float score;
}
