package source.dto.request.create_course;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AnswerDto {

    @JsonProperty("text")
    private String text;

    @JsonProperty("is_correct")
    private boolean isCorrect;
}