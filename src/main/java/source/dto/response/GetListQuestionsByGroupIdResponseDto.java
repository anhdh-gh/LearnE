package source.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.entity.Question;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetListQuestionsByGroupIdResponseDto {

    @JsonProperty("groupId")
    private String groupId;

    private List<Question> questions;
}
