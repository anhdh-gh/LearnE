package source.dto.request.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class AnswerRequestDto {

    @JsonProperty("text")
    private String text;

    @JsonProperty("is_correct")
    private boolean isCorrect;
}