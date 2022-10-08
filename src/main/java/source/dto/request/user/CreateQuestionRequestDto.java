package source.dto.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.dto.request.BasicRequest;
import source.dto.request.course.AnswerRequestDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateQuestionRequestDto extends BasicRequest {

    @JsonProperty("questionType")
    private String questionType;

    @JsonProperty("text")
    private String text;

    @JsonProperty("image")
    private String image;

    @JsonProperty("audio")
    private String audio;

    @JsonProperty("answers")
    private List<AnswerRequestDto> answers;
}
