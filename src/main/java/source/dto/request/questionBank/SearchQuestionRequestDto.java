package source.dto.request.questionBank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source.dto.request.BasicRequest;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class SearchQuestionRequestDto extends BasicRequest {

    private int page;

    private int size;

    private String text;

    private String userId;
}