package source.third_party.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.dto.request.BasicRequest;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetQuestionsByIdsRequestDto extends BasicRequest {
    private List<String> questionIds;
}
