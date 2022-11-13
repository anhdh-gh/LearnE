package source.dto.request.questionBank;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.dto.request.BasicRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetListQuestionsByGroupIdRequestDto extends BasicRequest {

    @JsonProperty("groupId")
    private String groupId;
}
