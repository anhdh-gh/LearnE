package source.dto.request.course;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source.dto.request.BasicRequest;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchCourseRequestDto extends BasicRequest {

    private int page;

    private int size;

    private String name;

    private String userId;
}
