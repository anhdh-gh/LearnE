package source.dto.request.questionBank;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.dto.request.BasicRequest;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateListQuestionsRequestDto extends BasicRequest {

    @JsonProperty("groupId")
    private String groupId;

    private List<CreateQuestionRequestDto> questions;
}
