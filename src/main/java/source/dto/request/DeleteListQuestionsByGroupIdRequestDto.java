package source.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteListQuestionsByGroupIdRequestDto extends BasicRequest {

    @JsonProperty("groupId")
    private String groupId;
}