package source.dto.request.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.dto.BaseDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDto extends BaseDto {

    private String text;

    @JsonProperty("isCorrect")
    private boolean isCorrect;

    @JsonProperty("isChoice")
    private boolean isChoice;
}
