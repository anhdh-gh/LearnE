package source.third_party.multimedia.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source.dto.request.BasicRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class QuestionDeleteByGroupIdRequestDto extends BasicRequest {

    private String groupId;
}
