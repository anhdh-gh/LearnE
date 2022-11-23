package source.dto.request.studyset;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source.dto.request.BasicRequest;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class SearchAllStudysetRequestDto extends BasicRequest {

    private int page;

    private int size;

    private String titleSearch;

    private String userId;
}
