package source.dto.request.question;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.dto.request.BasicRequest;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionGetByIdsRequestDto extends BasicRequest {
    private List<String> ids;
}
