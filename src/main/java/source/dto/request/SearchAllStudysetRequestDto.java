package source.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class SearchAllStudysetRequestDto extends BasicRequest {

    private int page;

    private int size;

    private String titleSearch;
}
