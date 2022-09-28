package source.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetQuestionByQuestionIdRequestDto extends BasicRequest {

    @JsonProperty("questionId")
    private String questionId;
}
