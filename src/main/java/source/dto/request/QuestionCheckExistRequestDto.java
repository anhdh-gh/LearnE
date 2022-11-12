package source.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source.entity.Question;
import source.entity.QuestionDetail;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class QuestionCheckExistRequestDto extends BasicRequest {

    private String groupId;
    private List<QuestionDetail> questions;
}
