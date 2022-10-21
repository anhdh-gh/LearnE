package source.dto.request.studyset;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source.dto.request.BasicRequest;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class CreateStudysetRequestDto extends BasicRequest {

    private String title;

    private String description;

    private List<WordCardDto> wordCards;
}
