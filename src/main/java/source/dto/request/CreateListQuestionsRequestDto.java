package source.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateListQuestionsRequestDto extends BasicRequest {

    private List<CreateQuestionRequestDto> questions;
}
