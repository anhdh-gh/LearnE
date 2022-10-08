package source.dto.request.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.dto.request.BasicRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionGetAllRequestDto extends BasicRequest {

    private int page;

    private int size;
}
