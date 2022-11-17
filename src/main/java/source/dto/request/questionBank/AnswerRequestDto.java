package source.dto.request.questionBank;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AnswerRequestDto {

    @JsonProperty("text")
    private String text;

    private boolean isCorrect;
}