package source.dto.request.course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import source.dto.request.BasicRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetCourseDetailRequestDto extends BasicRequest {

    private String userId;

    private String courseId;
}
