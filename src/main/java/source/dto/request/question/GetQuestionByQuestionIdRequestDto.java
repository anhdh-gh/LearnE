package source.dto.request.question;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.dto.request.BasicRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetQuestionByQuestionIdRequestDto extends BasicRequest {

    @JsonProperty("questionId")
    private String questionId;
}
