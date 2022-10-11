package source.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source.constant.StatusType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UpdateLessonStatusRequestDto extends BasicRequest {

    private String userId;

    private String lessonId;

    private StatusType status;
}