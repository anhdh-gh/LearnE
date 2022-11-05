package source.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CreateQuestionRequestDto extends BasicRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("questionType")
    private String questionType;

    @JsonProperty("text")
    private String text;

    @JsonProperty("image")
    private String image;

    @JsonProperty("audio")
    private String audio;

    @JsonProperty("header")
    private String header;

    @JsonProperty("groupId")
    private String groupId;

    @JsonProperty("answers")
    private List<AnswerRequestDto> answers;
}
