package source.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source.entity.enumeration.QuestionType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class QuestionGetListRequestDto extends BasicRequest {

    private QuestionType questionType;

    private long limit;
}
