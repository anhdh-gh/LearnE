package source.dto.request.questionBank;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source.dto.request.BasicRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class GetQuestionByQuestionIdRequestDto extends BasicRequest {

    @JsonProperty("questionId")
    private String questionId;
}
