package source.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source.entity.QuestionDetail;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class QuestionDeleteByGroupIdRequestDto extends BasicRequest {

    private String groupId;
}
