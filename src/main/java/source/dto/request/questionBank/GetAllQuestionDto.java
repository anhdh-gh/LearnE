package source.dto.request.questionBank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source.dto.request.BasicRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class GetAllQuestionDto extends BasicRequest {

    private int page;

    private int size;

    private String userId;
}
